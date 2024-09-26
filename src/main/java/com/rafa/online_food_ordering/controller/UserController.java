package com.rafa.online_food_ordering.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafa.online_food_ordering.model.Event;
import com.rafa.online_food_ordering.model.User;
import com.rafa.online_food_ordering.service.EventService;
import com.rafa.online_food_ordering.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @GetMapping("/profile")
    public ResponseEntity<User> findUserByJwtToken(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAllEvents(@RequestHeader("Authorization") String jwt) throws Exception {

        userService.findUserByJwtToken(jwt);
        List<Event> events = eventService.getAllEvent();

        return new ResponseEntity<>(events, HttpStatus.OK);

    }
}
