package com.satoge.expensetracker.controllers;

import com.satoge.expensetracker.dto.ExpenseDto;
import com.satoge.expensetracker.models.Expense;
import com.satoge.expensetracker.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/")
public class ExpenseController {
    private ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("expenses")
    public ResponseEntity<List<ExpenseDto>> getExpenses() {
        return ResponseEntity.ok(expenseService.getExpenses());
    }

    @GetMapping("/categories/{categoryId}/expenses")
    public ResponseEntity<List<ExpenseDto>> getExpensesByCategoryId(@PathVariable(value = "categoryId") int categoryId) {
        return ResponseEntity.ok(expenseService.getExpensesByCategoryId(categoryId));
    }

    @GetMapping("expenses/{id}")
    public ResponseEntity<ExpenseDto> getExpense(@PathVariable("id") int id) {
        return ResponseEntity.ok(expenseService.getExpenseById(id));
    }

    @PostMapping("expenses")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ExpenseDto> createExpense(@RequestBody ExpenseDto expenseDto) {
        return new ResponseEntity<>(expenseService.createExpense(expenseDto), HttpStatus.CREATED);
    }

    @PutMapping("expenses/{id}")
    public ResponseEntity<ExpenseDto> updateExpense(@RequestBody ExpenseDto expenseDto, @PathVariable("id") int id) {
        return new ResponseEntity<>(expenseService.updateExpenseById(expenseDto, id), HttpStatus.OK);
    }

    @DeleteMapping("expenses/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable("id") int id) {
        expenseService.deleteExpenseById(id);

        return new ResponseEntity<>("Expense deleted", HttpStatus.OK);
    }
}
