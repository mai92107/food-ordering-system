package com.rafa.online_food_ordering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafa.online_food_ordering.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

    public Cart findByCustomerId(Long userId);

}