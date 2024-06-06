package com.satoge.expensetracker.repositories;

import com.satoge.expensetracker.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
