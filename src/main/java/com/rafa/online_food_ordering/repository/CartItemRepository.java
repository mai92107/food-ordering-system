package com.rafa.online_food_ordering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafa.online_food_ordering.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
