package com.rafa.online_food_ordering.controller;

import com.rafa.online_food_ordering.model.Address;
import com.rafa.online_food_ordering.model.User;
import com.rafa.online_food_ordering.service.AddressService;
import com.rafa.online_food_ordering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity<Address> addUserAddress(@RequestHeader("Authorization") String jwt, @RequestBody Address address) throws Exception {
        System.out.println("我的jwt"+jwt);
        System.out.println("我的請求地址"+address);
        User user = userService.findUserByJwtToken(jwt);
        Address saved = addressService.createAddress(user, address);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserAddress(@RequestHeader("Authorization") String jwt, @PathVariable Long id) {
        addressService.deleteAddress(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Address>> getUserAddress(@RequestHeader("Authorization")String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Address> userAddress = addressService.getUserAddress(user);
        return new ResponseEntity<>(userAddress, HttpStatus.OK);
    }

}
