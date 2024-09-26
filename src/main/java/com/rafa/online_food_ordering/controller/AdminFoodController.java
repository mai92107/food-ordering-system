package com.rafa.online_food_ordering.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafa.online_food_ordering.model.Food;
import com.rafa.online_food_ordering.model.Restaurant;
import com.rafa.online_food_ordering.request.CreateFoodRequest;
import com.rafa.online_food_ordering.response.MessageResponse;
import com.rafa.online_food_ordering.service.FoodService;
import com.rafa.online_food_ordering.service.RestaurantService;
import com.rafa.online_food_ordering.service.UserService;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req,
            @RequestHeader("Authorization") String jwt) throws Exception {

        userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.findRestaurantById(req.getRestaurantId());

        Food food = foodService.createFood(req, req.getCategory(), restaurant);

        return new ResponseEntity<>(food, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id,
            @RequestHeader("Authorization") String jwt) throws Exception {

        userService.findUserByJwtToken(jwt);

        foodService.deleteFood(id);
        MessageResponse msg = new MessageResponse();
        msg.setMessage("deleted successfully");

        return new ResponseEntity<>(msg, HttpStatus.OK);
    }



    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFoodAvailabilityStatus(@PathVariable Long id,
            @RequestHeader("Authorization") String jwt) throws Exception {

        userService.findUserByJwtToken(jwt);

        Food food = foodService.updateAvailabilityStatus(id);

        return new ResponseEntity<>(food, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Food>> getFoodByCategoryId(
            @PathVariable Long categoryId,
            @RequestHeader("Authorization") String jwt) throws Exception {

        userService.findUserByJwtToken(jwt);

        List<Food> foods = foodService.getFoodByCategoryId(categoryId);

        return new ResponseEntity<>(foods, HttpStatus.OK);
    }
}
