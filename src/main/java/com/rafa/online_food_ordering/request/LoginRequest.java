package com.rafa.online_food_ordering.request;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;
    private String password;
}
