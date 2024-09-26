package com.rafa.online_food_ordering.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafa.online_food_ordering.model.Category;
import com.rafa.online_food_ordering.model.Food;
import com.rafa.online_food_ordering.model.Restaurant;
import com.rafa.online_food_ordering.repository.FoodRepository;
import com.rafa.online_food_ordering.request.CreateFoodRequest;

@Service
public class FoodServiceImp implements FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Override
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) {

        Food food = new Food();
        food.setFoodCategory(category);
        food.setRestaurant(restaurant);
        food.setDescription(req.getDescription());
        food.setImages(req.getImages());
        food.setName(req.getName());
        food.setPrice(req.getPrice());
        food.setIngredients(req.getIngredients());
        food.setSeasonal(req.isSeasonal());
        food.setVegetarian(req.isVegetarian());
        food.setCreationDate(new Date());

        Food saveFood = foodRepository.save(food);
        restaurant.getFoods().add(saveFood);

        return saveFood;
    }

    @Override
    public void deleteFood(Long foodId) throws Exception {

        Food food = findFoodById(foodId);
        food.setRestaurant(null);
        foodRepository.save(food);
    }

    @Override
    public List<Food> getRestaurantFood(Long restaurantId, boolean isVegetarian,
            boolean isNoVeg, boolean isSeasonal, String foodCategory) {

        List<Food> foods = foodRepository.findByRestaurantId(restaurantId);

        if (isVegetarian) {
            foods = filterByVegetarian(foods);
        }
        if (isNoVeg) {
            foods = filterByNoVeg(foods);
        }
        if (isSeasonal) {
            foods = filterBySeasonal(foods);
        }
        if (foodCategory != null && !foodCategory.equals("")) {
            foods = filterByCategory(foods, foodCategory);
        }
        return foods;
    }

    private List<Food> filterByCategory(List<Food> foods, String foodCategory) {

        return foods.stream().filter(f -> {
            if (f.getFoodCategory() != null) {
                return f.getFoodCategory().getName().equals(foodCategory);
            }
            return false;

        }).collect(Collectors.toList());

    }

    private List<Food> filterBySeasonal(List<Food> foods) {

        return foods.stream().filter(f -> f.isSeasonal() == true).collect(Collectors.toList());
    }

    private List<Food> filterByNoVeg(List<Food> foods) {

        return foods.stream().filter(f -> f.isVegetarian() == false).collect(Collectors.toList());
    }

    private List<Food> filterByVegetarian(List<Food> foods) {

        return foods.stream().filter(f -> f.isVegetarian() == true).collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFood(String keyWord) {
        return foodRepository.searchFood(keyWord);
    }

    @Override
    public Food findFoodById(Long foodId) throws Exception {
        Optional<Food> optionalFood = foodRepository.findById(foodId);
        if (optionalFood.isEmpty()) {
            throw new Exception("food not exist...");
        }
        return optionalFood.get();
    }

    @Override
    public Food updateAvailabilityStatus(Long foodId) throws Exception {

        Food food = findFoodById(foodId);
        food.setAvailable(!food.isAvailable());
        return foodRepository.save(food);
    }

    @Override
    public List<Food> getFoodByCategoryId(Long categoryId) throws Exception {

        List<Food> foods = foodRepository.findFoodByFoodCategoryId(categoryId);
        return foods;
    }

}
