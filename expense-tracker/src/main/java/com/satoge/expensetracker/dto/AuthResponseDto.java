package com.satoge.expensetracker.dto;

import com.satoge.expensetracker.models.User;
import lombok.Data;

@Data
public class AuthResponseDto {
    private String accessToken;
    private String tokenType = "Bearer ";
    private int userId;
    private String username;

    public AuthResponseDto(String accessToken, int userId, String username) {
        this.accessToken = accessToken;
        this.userId = userId;
        this.username = username;
    }
}
