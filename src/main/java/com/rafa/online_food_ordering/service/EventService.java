package com.rafa.online_food_ordering.service;

import java.util.List;


import com.rafa.online_food_ordering.model.Event;
import com.rafa.online_food_ordering.model.Restaurant;
import com.rafa.online_food_ordering.request.CreateEventRequest;

public interface EventService {

    public Event createEvent(CreateEventRequest req, Restaurant restaurant);

    public void deleteEvent(Long id) throws Exception;

    public Event findEventById(Long id) throws Exception;

    public List<Event> getRestaurantEvent(Long restaurantId);

    public List<Event> getAllEvent();

}
