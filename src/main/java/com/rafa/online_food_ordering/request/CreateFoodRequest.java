package com.rafa.online_food_ordering.request;

import java.util.List;

import com.rafa.online_food_ordering.model.Category;
import com.rafa.online_food_ordering.model.IngredientsItem;

import lombok.Data;

@Data
public class CreateFoodRequest {

    private String name;
    private String description;
    private int price;
    private Category category;
    private List<String> images;
    private Long restaurantId;
    private boolean vegetarian;
    private boolean seasonal;
    private List<IngredientsItem> ingredients;
}
