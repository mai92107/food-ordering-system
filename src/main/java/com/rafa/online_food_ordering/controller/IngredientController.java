package com.rafa.online_food_ordering.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafa.online_food_ordering.model.IngredientCategory;
import com.rafa.online_food_ordering.model.IngredientsItem;
import com.rafa.online_food_ordering.request.IngredientCategoryRequest;
import com.rafa.online_food_ordering.request.IngredientItemRequest;
import com.rafa.online_food_ordering.service.IngredientService;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @PostMapping("/category")
    public ResponseEntity<IngredientCategory> createIngredientCategory(
            @RequestBody IngredientCategoryRequest req) throws Exception {
        IngredientCategory category = ingredientService.createIngredientCategory(req.getName(), req.getRestaurantId());

        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<IngredientsItem> createIngredientItem(
            @RequestBody IngredientItemRequest req) throws Exception {
        IngredientsItem item = ingredientService.createIngredientItem(req.getRestaurantId(), req.getName(),
                req.getCategoryId());

        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<IngredientsItem> updateIngredientStock(
            @PathVariable Long id) throws Exception {
        IngredientsItem item = ingredientService.updateStock(id);

        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngredientsItem>> restaurantIngredient(
            @PathVariable Long id) throws Exception {
        List<IngredientsItem> items = ingredientService.findRestaurantIngredientsByRestaurantId(id);

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngredientCategory>> restaurantIngredientCategory(
            @PathVariable Long id) throws Exception {
        List<IngredientCategory> items = ingredientService.findIngredientCategoryByRestaurantId(id);

        return new ResponseEntity<>(items, HttpStatus.OK);
    }
}
