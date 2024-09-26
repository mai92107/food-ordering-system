package com.rafa.online_food_ordering.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafa.online_food_ordering.model.IngredientCategory;

public interface IngredientCategoryRepository extends JpaRepository<IngredientCategory, Long> {

    List<IngredientCategory> findByRestaurantId(Long restaurantId);
}
