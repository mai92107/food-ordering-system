package com.rafa.online_food_ordering.request;

import java.util.List;

import com.rafa.online_food_ordering.model.Address;
import com.rafa.online_food_ordering.model.ContactInformation;

import lombok.Data;

@Data
public class CreateRestaurantRequest {

    private Long id;
    private String name;
    private String description;
    private String cuisineType;
    private Address address;
    private ContactInformation contactInformation;
    private String openingTime;
    private List<String> images;

}
