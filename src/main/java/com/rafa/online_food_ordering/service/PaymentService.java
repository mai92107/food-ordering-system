package com.rafa.online_food_ordering.service;

import org.springframework.stereotype.Service;

import com.rafa.online_food_ordering.model.Order;
import com.rafa.online_food_ordering.response.PaymentResponse;

@Service
public interface PaymentService{
    
    public PaymentResponse createPaymentLink(Order order); 
}
