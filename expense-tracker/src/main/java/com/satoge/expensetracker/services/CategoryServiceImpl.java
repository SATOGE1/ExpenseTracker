package com.satoge.expensetracker.services;

import com.satoge.expensetracker.dto.CategoryDto;
import com.satoge.expensetracker.dto.ExpenseDto;
import com.satoge.expensetracker.exceptions.CategoryNotFoundException;
import com.satoge.expensetracker.exceptions.UserNotFoundException;
import com.satoge.expensetracker.models.Category;
import com.satoge.expensetracker.models.Expense;
import com.satoge.expensetracker.models.User;
import com.satoge.expensetracker.repositories.CategoryRepository;
import com.satoge.expensetracker.repositories.ExpenseRepository;
import com.satoge.expensetracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    public CategoryServiceImpl(ExpenseRepository expenseRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category newCategory = categoryRepository.save(mapToEntity(categoryDto));

        return mapToDto(newCategory);
    }

    @Override
    public List<CategoryDto> getCategories(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found exception"));
        List<Expense> expenses = expenseRepository.findByUserId(user.getId());

        List<Category> categories = categoryRepository.findAll();

        List<CategoryDto> categoriesWithTotals = new ArrayList<>();

        for (Category category : categories) {
            CategoryDto categoryDto = mapToDto(category);

            double total = expenses.stream()
                .filter(expense -> expense.getCategory().equals(category))
                .mapToDouble(Expense::getAmount)
                .sum();

            categoryDto.setTotal(total);
            categoriesWithTotals.add(categoryDto);
        }

        return categoriesWithTotals;
    }

    @Override
    public CategoryDto getCategoryById(int id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found exception"));

        return mapToDto(category);
    }

    @Override
    public CategoryDto updateCategoryById(CategoryDto categoryDto, int id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found exception"));

        category.setTitle(categoryDto.getTitle());

        Category updatedCategory = categoryRepository.save(category);

        return mapToDto(updatedCategory);
    }

    @Override
    public void deleteCategoryById(int id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found exception"));

        categoryRepository.delete(category);
    }

    private CategoryDto mapToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setTitle(category.getTitle());

        return categoryDto;
    }

    private Category mapToEntity(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setTitle(categoryDto.getTitle());

        return category;
    }

    private CategoryRepository categoryRepository;
    private ExpenseRepository expenseRepository;
    private UserRepository userRepository;
}
