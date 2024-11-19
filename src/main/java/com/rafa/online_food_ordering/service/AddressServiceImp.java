package com.rafa.online_food_ordering.service;

import com.rafa.online_food_ordering.model.Address;
import com.rafa.online_food_ordering.model.User;
import com.rafa.online_food_ordering.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImp implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Override
    public Address createAddress(User user, Address address) {
        address.setUser(user);
        return addressRepository.save(address);
    }

    @Override
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public List<Address> getUserAddress(User user) {
        return addressRepository.findByUserId(user.getId());
    }
}
