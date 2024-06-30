package com.satoge.expensetracker.services;

import com.satoge.expensetracker.dto.ExpenseDto;

import java.util.List;

public interface ExpenseService {
    ExpenseDto createExpense(ExpenseDto expenseDto, String username);

    List<ExpenseDto> getExpenses(String username);

    List<ExpenseDto> getExpensesByCategoryId(int categoryId);

    ExpenseDto getExpenseById(int id);

    ExpenseDto updateExpenseById(ExpenseDto expenseDto, int id);

    void deleteExpenseById(int id);
}
