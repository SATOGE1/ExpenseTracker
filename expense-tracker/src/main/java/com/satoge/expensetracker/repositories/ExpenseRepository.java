package com.satoge.expensetracker.repositories;

import com.satoge.expensetracker.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
    List<Expense> findByCategoryId(int categoryId);

    List<Expense> findByUserId(int userId);
}
