package com.rafa.online_food_ordering.service;

import java.util.List;

import com.rafa.online_food_ordering.model.Category;
import com.rafa.online_food_ordering.model.Food;
import com.rafa.online_food_ordering.model.Restaurant;
import com.rafa.online_food_ordering.request.CreateFoodRequest;

public interface FoodService {

    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);

    public void deleteFood(Long id) throws Exception;

    public List<Food> getRestaurantFood(Long restaurantId, boolean isVegetarian,
            boolean isNoVeg, boolean isSeasonal,
            String foodCategory);

    public List<Food> searchFood(String keyWord);

    public Food findFoodById(Long foodId) throws Exception;

    public Food updateAvailabilityStatus(Long foodId) throws Exception;

    public List<Food> getFoodByCategoryId(Long categoryId) throws Exception;
}
