package com.rafa.online_food_ordering.request;


import lombok.Data;

@Data
public class CreateEventRequest {
    Long restaurantId;
    String image;
    String location;
    String name;
    String startTime;
    String endTime;
    String description;
    
}