package com.satoge.expensetracker.services;

import com.satoge.expensetracker.dto.ExpenseDto;
import com.satoge.expensetracker.exceptions.CategoryNotFoundException;
import com.satoge.expensetracker.exceptions.ExpenseNotFoundException;
import com.satoge.expensetracker.exceptions.UserNotFoundException;
import com.satoge.expensetracker.models.Category;
import com.satoge.expensetracker.models.Expense;
import com.satoge.expensetracker.models.User;
import com.satoge.expensetracker.repositories.CategoryRepository;
import com.satoge.expensetracker.repositories.ExpenseRepository;
import com.satoge.expensetracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    public ExpenseServiceImpl(ExpenseRepository expenseRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ExpenseDto createExpense(ExpenseDto expenseDto, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found exception"));
        expenseDto.setUserId(user.getId());

        Expense newExpense = expenseRepository.save(mapToEntity(expenseDto));

        return mapToDto(newExpense);
    }

    @Override
    public List<ExpenseDto> getExpenses(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found exception"));
        List<Expense> expenses = expenseRepository.findByUserId(user.getId());

        return expenses.stream().map(expense -> mapToDto(expense)).collect(Collectors.toList());
    }

    @Override
    public List<ExpenseDto> getExpensesByCategoryId(int id) {
        categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found exception"));

        List<Expense> expenses = expenseRepository.findByCategoryId(id);

        return expenses.stream().map(expense -> mapToDto(expense)).collect(Collectors.toList());
    }

    @Override
    public ExpenseDto getExpenseById(int id) {
        Expense expense = expenseRepository.findById(id).orElseThrow(() -> new ExpenseNotFoundException("Expense not found exception"));

        return mapToDto(expense);
    }

    @Override
    public ExpenseDto updateExpenseById(ExpenseDto expenseDto, int id) {
        Expense expense = expenseRepository.findById(id).orElseThrow(() -> new ExpenseNotFoundException("Expense not found exception"));

        expense.setDescription(expenseDto.getDescription());
        expense.setAmount(expenseDto.getAmount());
        expense.setDate(expenseDto.getDate());

        Category category = categoryRepository.findById(expenseDto.getCategoryId()).orElseThrow(() -> new CategoryNotFoundException("Category not found exception"));

        expense.setCategory(category);

        Expense updatedExpense = expenseRepository.save(expense);

        return mapToDto(updatedExpense);
    }

    @Override
    public void deleteExpenseById(int id) {
        Expense expense = expenseRepository.findById(id).orElseThrow(() -> new ExpenseNotFoundException("Expense not found exception"));

        expenseRepository.delete(expense);
    }

    private ExpenseDto mapToDto(Expense expense) {
        ExpenseDto expenseDto = new ExpenseDto();

        expenseDto.setId(expense.getId());
        expenseDto.setUserId(expense.getUser().getId());
        expenseDto.setAmount(expense.getAmount());
        expenseDto.setDescription(expense.getDescription());
        expenseDto.setDate(expense.getDate());
        expenseDto.setCategoryId(expense.getCategory().getId());

        return expenseDto;
    }

    private Expense mapToEntity(ExpenseDto expenseDto) {
        Expense expense = new Expense();

        expense.setId(expenseDto.getId());
        expense.setAmount(expenseDto.getAmount());
        expense.setDescription(expenseDto.getDescription());
        expense.setDate(expenseDto.getDate());

        Category category = categoryRepository.findById(expenseDto.getCategoryId()).orElseThrow(() -> new CategoryNotFoundException("Category not found exception"));

        expense.setCategory(category);

        User user = userRepository.findById(expenseDto.getUserId()).orElseThrow(() -> new UserNotFoundException("User not found exception"));

        expense.setUser(user);

        expense = expenseRepository.save(expense);

        return expense;
    }

    private ExpenseRepository expenseRepository;
    private CategoryRepository categoryRepository;
    private UserRepository userRepository;
}
