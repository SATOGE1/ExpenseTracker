package com.satoge.expensetracker.services;

import com.satoge.expensetracker.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto expenseDto);

    List<CategoryDto> getCategories();

    CategoryDto getCategoryById(int id);

    CategoryDto updateCategoryById(CategoryDto expenseDto, int id);

    void deleteCategoryById(int id);
}
