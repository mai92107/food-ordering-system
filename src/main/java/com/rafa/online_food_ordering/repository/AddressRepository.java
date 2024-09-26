package com.rafa.online_food_ordering.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rafa.online_food_ordering.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
