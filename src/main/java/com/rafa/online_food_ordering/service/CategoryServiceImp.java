package com.rafa.online_food_ordering.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafa.online_food_ordering.model.Category;
import com.rafa.online_food_ordering.model.Restaurant;
import com.rafa.online_food_ordering.repository.CategoryRepository;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(String name, Long userId) throws Exception {

        Restaurant restaurant = restaurantService.getRestaurantByUserId(userId);

        Category category = new Category();
        category.setName(name);
        category.setRestaurant(restaurant);

        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findCategoryByUserId(Long userId) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantByUserId(userId);

        return categoryRepository.findByRestaurantId(restaurant.getId());
    }

    @Override
    public List<Category> findCategoryByRestaurantId(Long id) throws Exception {

        List<Category> category = categoryRepository.findByRestaurantId(id);

        return category;

    }

    @Override
    public void removeCategoryById(Long id) throws Exception {
        categoryRepository.deleteById(id);
    }

    

}
