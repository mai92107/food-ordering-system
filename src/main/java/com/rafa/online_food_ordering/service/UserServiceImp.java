package com.rafa.online_food_ordering.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafa.online_food_ordering.config.JwtProvider;
import com.rafa.online_food_ordering.model.User;
import com.rafa.online_food_ordering.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserByJwtToken(String jwt) throws Exception {

        String email = jwtProvider.getEmailFromJwtToken(jwt);

        User user = findUserByEmail(email);
        return user;

    }

    @Override
    public User findUserByEmail(String email) throws Exception {

        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new Exception("user not found...");
        }
        return user;

    }

}
