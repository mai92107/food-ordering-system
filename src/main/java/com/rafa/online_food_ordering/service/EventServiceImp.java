package com.rafa.online_food_ordering.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafa.online_food_ordering.model.Event;
import com.rafa.online_food_ordering.model.Restaurant;
import com.rafa.online_food_ordering.repository.EventRepository;
import com.rafa.online_food_ordering.repository.RestaurantRepository;
import com.rafa.online_food_ordering.request.CreateEventRequest;

@Service
public class EventServiceImp implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Event createEvent(CreateEventRequest req, Restaurant restaurant) {

        Event event = new Event();
        event.setName(req.getName());
        event.setImage(req.getImage());
        event.setLocation(req.getLocation());
        event.setStartTime(req.getStartTime());
        event.setEndTime(req.getEndTime());
        event.setRestaurant(restaurant);
        event.setDescription(req.getDescription());

        Event newEvent = eventRepository.save(event);
        restaurant.getEvents().add(newEvent);
        return newEvent;
    }

    @Override
    public void deleteEvent(Long id) throws Exception {
        eventRepository.deleteById(id);
    }

    @Override
    public Event findEventById(Long id) throws Exception {
        Optional<Event> optionalEvent = eventRepository.findById(id);

        if (optionalEvent.isEmpty()) {
            throw new Exception("Event not found...");
        }
        return optionalEvent.get();
    }

    @Override
    public List<Event> getRestaurantEvent(Long restaurantId) {
        List<Event> events = eventRepository.findByRestaurantId(restaurantId);
        return events;
    }

    @Override
    public List<Event> getAllEvent() {

        return eventRepository.findAll();
    }

}
