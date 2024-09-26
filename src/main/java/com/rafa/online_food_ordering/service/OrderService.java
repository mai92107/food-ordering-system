package com.rafa.online_food_ordering.service;

import java.util.List;


import com.rafa.online_food_ordering.model.Order;
import com.rafa.online_food_ordering.model.User;
import com.rafa.online_food_ordering.request.OrderRequest;

public interface OrderService {

    public Order createOrder(OrderRequest order, User user) throws Exception;

    public Order updateOrder(Long orderId, String orderStatus) throws Exception;

    public void cancelOrder(Long orderId) throws Exception;

    public List<Order> getUserOder(Long userId) throws Exception;

    public List<Order> getRestaurantOder(Long restaurantId, String orderStatus) throws Exception;

    public Order findOrderById(Long orderId) throws Exception;
}
