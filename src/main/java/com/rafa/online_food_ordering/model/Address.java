package com.rafa.online_food_ordering.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;
    private String streetAddress;
    private String city;
    private String name;
    private String country;

}
