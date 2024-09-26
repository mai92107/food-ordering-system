package com.rafa.online_food_ordering.response;

import com.rafa.online_food_ordering.model.USER_ROLE;

import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private USER_ROLE role;
}
