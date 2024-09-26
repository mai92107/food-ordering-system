package com.rafa.online_food_ordering.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafa.online_food_ordering.model.Order;
import com.rafa.online_food_ordering.model.User;
import com.rafa.online_food_ordering.request.OrderRequest;
import com.rafa.online_food_ordering.response.PaymentResponse;
import com.rafa.online_food_ordering.service.OrderService;
import com.rafa.online_food_ordering.service.PaymentService;
import com.rafa.online_food_ordering.service.UserService;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping("/order")
    public ResponseEntity<PaymentResponse> createOrder(
            @RequestBody OrderRequest req,
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        Order order = orderService.createOrder(req, user);
        PaymentResponse res = paymentService.createPaymentLink(order);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/order/user")
    public ResponseEntity<List<Order>> getOrderHistory(
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        List<Order> orders = orderService.getUserOder(user.getId());

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
