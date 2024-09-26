package com.rafa.online_food_ordering.service;

import java.util.List;

import com.rafa.online_food_ordering.model.IngredientCategory;
import com.rafa.online_food_ordering.model.IngredientsItem;

public interface IngredientService {

    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception;

    public IngredientCategory findIngredientCategoryById(Long id) throws Exception;

    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long restaurantId) throws Exception;

    public List<IngredientsItem> findRestaurantIngredientsByRestaurantId(Long restaurantId) throws Exception;

    public IngredientsItem createIngredientItem(Long restaurantId, String ingredientName, Long ingredientCategoryId)
            throws Exception;

    public IngredientsItem updateStock(Long id) throws Exception;

}
