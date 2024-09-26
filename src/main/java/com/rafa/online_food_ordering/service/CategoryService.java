package com.rafa.online_food_ordering.service;

import java.util.List;

import com.rafa.online_food_ordering.model.Category;

public interface CategoryService {

    public Category createCategory(String name, Long userId) throws Exception;

    public List<Category> findCategoryByUserId(Long userId) throws Exception;

    public List<Category> findCategoryByRestaurantId(Long id) throws Exception;

    public void removeCategoryById(Long id) throws Exception;

}
