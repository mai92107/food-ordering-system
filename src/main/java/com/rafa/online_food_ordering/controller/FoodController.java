package com.rafa.online_food_ordering.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rafa.online_food_ordering.model.Food;
import com.rafa.online_food_ordering.service.FoodService;
import com.rafa.online_food_ordering.service.UserService;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String name,
            @RequestHeader("Authorization") String jwt) throws Exception {

        userService.findUserByJwtToken(jwt);

        List<Food> foods = foodService.searchFood(name);

        return new ResponseEntity<>(foods, HttpStatus.CREATED);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(
            @PathVariable(required = false)  Long restaurantId,
            @RequestParam(required = false)  boolean vegetarian,
            @RequestParam(required = false)  boolean notVeg,
            @RequestParam(required = false)  boolean seasonal,
            @RequestParam(required = false) String food_category,
            @RequestHeader("Authorization") String jwt) throws Exception {

        userService.findUserByJwtToken(jwt);

        List<Food> foods = foodService.getRestaurantFood(restaurantId, vegetarian, notVeg, seasonal, food_category);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }
}
