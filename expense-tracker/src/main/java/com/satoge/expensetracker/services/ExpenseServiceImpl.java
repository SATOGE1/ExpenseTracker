package com.satoge.expensetracker.services;

import com.satoge.expensetracker.dto.ExpenseDto;
import com.satoge.expensetracker.exceptions.CategoryNotFoundException;
import com.satoge.expensetracker.exceptions.ExpenseNotFoundException;
import com.satoge.expensetracker.models.Category;
import com.satoge.expensetracker.models.Expense;
import com.satoge.expensetracker.repositories.CategoryRepository;
import com.satoge.expensetracker.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    public ExpenseServiceImpl(ExpenseRepository expenseRepository, CategoryRepository categoryRepository) {
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ExpenseDto createExpense(ExpenseDto expenseDto) {
        Expense newExpense = expenseRepository.save(mapToEntity(expenseDto));

        return mapToDto(newExpense);
    }

    @Override
    public List<ExpenseDto> getExpenses() {
        List<Expense> expenses = expenseRepository.findAll();

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

        expense = expenseRepository.save(expense);

        return expense;
    }

    private ExpenseRepository expenseRepository;
    private CategoryRepository categoryRepository;
}
