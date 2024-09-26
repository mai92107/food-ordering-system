package com.rafa.online_food_ordering.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rafa.online_food_ordering.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    
    List<Event> findByRestaurantId(Long restaurantId);
}
