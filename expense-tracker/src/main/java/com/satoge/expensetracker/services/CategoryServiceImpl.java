package com.satoge.expensetracker.services;

import com.satoge.expensetracker.dto.CategoryDto;
import com.satoge.expensetracker.exceptions.CategoryNotFoundException;
import com.satoge.expensetracker.models.Category;
import com.satoge.expensetracker.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category newCategory = categoryRepository.save(mapToEntity(categoryDto));

        return mapToDto(newCategory);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream().map(category -> mapToDto(category)).collect(Collectors.toList());
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
}
