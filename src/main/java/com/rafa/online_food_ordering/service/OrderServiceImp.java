package com.rafa.online_food_ordering.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafa.online_food_ordering.model.Address;
import com.rafa.online_food_ordering.model.Cart;
import com.rafa.online_food_ordering.model.CartItem;
import com.rafa.online_food_ordering.model.Order;
import com.rafa.online_food_ordering.model.OrderItem;
import com.rafa.online_food_ordering.model.Restaurant;
import com.rafa.online_food_ordering.model.User;
import com.rafa.online_food_ordering.repository.AddressRepository;
import com.rafa.online_food_ordering.repository.OrderItemRepository;
import com.rafa.online_food_ordering.repository.OrderRepository;
import com.rafa.online_food_ordering.repository.UserRepository;
import com.rafa.online_food_ordering.request.OrderRequest;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CartService cartService;

    @Override
    public Order createOrder(OrderRequest order, User user) throws Exception {

        Address shippingAddress = order.getDeliveryAddress();
        Address savedAddress = addressRepository.save(shippingAddress);
        if (!user.getAddresses().contains(savedAddress)) {
            user.getAddresses().add(savedAddress);
            userRepository.save(user);
        }
        Restaurant restaurant = restaurantService.findRestaurantById(order.getRestaurantId());
        Order newOrder = new Order();
        newOrder.setCustomer(user);
        newOrder.setDeliveryAddress(savedAddress);
        newOrder.setCreatedAt(new Date());
        newOrder.setOrderStatus("PENDING");
        newOrder.setRestaurant(restaurant);

        Cart cart = cartService.findCartByUserId(user.getId());
        System.out.println("create cart");
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setFood(cartItem.getFood());
            orderItem.setIngredients(cartItem.getIngredients());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setTotalPrice(cartItem.getTotalPrice());

            OrderItem savedItem = orderItemRepository.save(orderItem);
            orderItems.add(savedItem);
        }
        System.out.println("create order");

        newOrder.setItems(orderItems);
        newOrder.setTotalPrice(cartService.calculateCartTotal(cart));
        System.out.println("total is $ " + cartService.calculateCartTotal(cart));

        Order savedOrder = orderRepository.save(newOrder);
        restaurant.getOrders().add(savedOrder);

        return savedOrder;
    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws Exception {

        Order order = findOrderById(orderId);
        if (orderStatus.equals("OUT_FOR_DELIVERY") ||
                orderStatus.equals("DELIVERING") ||
                orderStatus.equals("COMPLETED") ||
                orderStatus.equals("PENDING")) {
            order.setOrderStatus(orderStatus);
            return orderRepository.save(order);
        }
        throw new Exception("Please select a valid order status...");
    }

    @Override
    public void cancelOrder(Long orderId) throws Exception {

        orderRepository.deleteById(orderId);
    }

    @Override
    public List<Order> getUserOder(Long userId) throws Exception {

        return orderRepository.findByCustomerId(userId);
    }

    @Override
    public List<Order> getRestaurantOder(Long restaurantId, String orderStatus) throws Exception {

        List<Order> orders = orderRepository.findByRestaurantId(restaurantId);

        if (orderStatus != null) {
            orders = orders.stream().filter(order -> order.getOrderStatus().equals(orderStatus))
                    .collect(Collectors.toList());
        }
        return orders;
    }

    @Override
    public Order findOrderById(Long orderId) throws Exception {

        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isEmpty()) {
            throw new Exception("order not found");
        }
        return optionalOrder.get();
    }

}
