package com.rafa.online_food_ordering.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafa.online_food_ordering.model.Event;
import com.rafa.online_food_ordering.model.Restaurant;
import com.rafa.online_food_ordering.request.CreateEventRequest;
import com.rafa.online_food_ordering.response.MessageResponse;
import com.rafa.online_food_ordering.service.EventService;
import com.rafa.online_food_ordering.service.RestaurantService;
import com.rafa.online_food_ordering.service.UserService;

@RestController
@RequestMapping("/api/admin/events")
public class AdminEventController {

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private EventService eventService;

    @PostMapping("/restaurant/{id}")
    public ResponseEntity<Event> createEvent(@RequestBody CreateEventRequest req, @PathVariable Long id,

            @RequestHeader("Authorization") String jwt) throws Exception {

        userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.findRestaurantById(id);

        Event event = eventService.createEvent(req, restaurant);

        return new ResponseEntity<>(event, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteEvent(@PathVariable Long id,

            @RequestHeader("Authorization") String jwt) throws Exception {

        userService.findUserByJwtToken(jwt);

        eventService.deleteEvent(id);

        MessageResponse msg = new MessageResponse();
        msg.setMessage("deleted successfully");

        return new ResponseEntity<>(msg, HttpStatus.OK);

    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<Event>> getRestaurantEventsByRestaurantId(@PathVariable Long id,
            @RequestHeader("Authorization") String jwt) throws Exception {

        userService.findUserByJwtToken(jwt);

        List<Event> events = eventService.getRestaurantEvent(id);

        return new ResponseEntity<>(events, HttpStatus.OK);

    }

}
