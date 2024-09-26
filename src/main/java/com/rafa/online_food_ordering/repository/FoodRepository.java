package com.rafa.online_food_ordering.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rafa.online_food_ordering.model.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findByRestaurantId(Long restaurantId);
    
    List<Food> findFoodByFoodCategoryId(Long foodCategoryId);


    @Query("SELECT f FROM Food f WHERE f.name LIKE %:keyword% OR f.foodCategory.name LIKE %:keyword%")
    List<Food> searchFood(@Param("keyword") String keyword);

}
