package com.satoge.expensetracker.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ExpenseDto {
    private int id;
    private Date date;
    private String description;
    private double amount;
    private int categoryId;
    private int userId;

    @Override
    public String toString() {
        return "ExpenseDto{" +
            "id=" + id +
            ", date=" + date +
            ", description='" + description + '\'' +
            ", amount=" + amount +
            ", categoryId=" + categoryId +
            ", userId=" + userId +
            '}';
    }
}
