package com.rafa.online_food_ordering.service;

import com.rafa.online_food_ordering.model.User;

public interface UserService {

    public User findUserByJwtToken(String jwt) throws Exception;

    public User findUserByEmail(String email) throws Exception;

}