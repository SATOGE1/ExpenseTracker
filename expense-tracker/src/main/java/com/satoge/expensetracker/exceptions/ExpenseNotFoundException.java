package com.satoge.expensetracker.exceptions;

import java.io.Serial;

public class ExpenseNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1;

    public ExpenseNotFoundException(String message) {
        super(message);
    }
}
