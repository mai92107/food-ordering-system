package com.rafa.online_food_ordering.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafa.online_food_ordering.model.IngredientsItem;

public interface IngredientItemRepository extends JpaRepository<IngredientsItem, Long> {

    List<IngredientsItem> findByRestaurantId(Long id);
}
