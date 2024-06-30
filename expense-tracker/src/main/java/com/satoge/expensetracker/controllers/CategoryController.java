package com.satoge.expensetracker.controllers;

import com.satoge.expensetracker.dto.CategoryDto;
import com.satoge.expensetracker.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("categories")
    public ResponseEntity<List<CategoryDto>> getCategories() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return ResponseEntity.ok(categoryService.getCategories(currentPrincipalName));
    }

    @GetMapping("categories/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("id") int id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PostMapping("categories")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(categoryService.createCategory(categoryDto), HttpStatus.CREATED);
    }

    @PutMapping("categories/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable("id") int id) {
        return new ResponseEntity<>(categoryService.updateCategoryById(categoryDto, id), HttpStatus.OK);
    }

    @DeleteMapping("categories/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") int id) {
        categoryService.deleteCategoryById(id);

        return new ResponseEntity<>("Category deleted", HttpStatus.OK);
    }
}
