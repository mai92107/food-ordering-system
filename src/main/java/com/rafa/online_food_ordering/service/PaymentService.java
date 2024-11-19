package com.rafa.online_food_ordering.service;

import com.rafa.online_food_ordering.model.Order;
import com.rafa.online_food_ordering.response.PaymentResponse;

public interface PaymentService {

    public PaymentResponse createPaymentLink(Order order);
}
