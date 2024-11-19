package com.rafa.online_food_ordering.service;

import com.rafa.online_food_ordering.model.Address;
import com.rafa.online_food_ordering.model.User;

import java.util.List;

public interface AddressService {
    Address createAddress(User user,Address address);
    void deleteAddress(Long id);

    List<Address> getUserAddress(User user);
}
