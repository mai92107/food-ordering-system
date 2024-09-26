package com.rafa.online_food_ordering.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafa.online_food_ordering.model.Cart;
import com.rafa.online_food_ordering.model.CartItem;
import com.rafa.online_food_ordering.model.Food;
import com.rafa.online_food_ordering.model.User;
import com.rafa.online_food_ordering.repository.CartItemRepository;
import com.rafa.online_food_ordering.repository.CartRepository;
import com.rafa.online_food_ordering.request.AddCartItemRequest;

@Service
public class CartServiceImp implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private FoodService foodService;

    @Override
    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception {
        System.out.println("my request :  " + req);
        User user = userService.findUserByJwtToken(jwt);
        Food food = foodService.findFoodById(req.getFoodId());
        Cart cart = cartRepository.findByCustomerId(user.getId());

        for (CartItem cartItem : cart.getItems()) {
            if (cartItem.getFood().equals(food)) {
                int newQuantity = cartItem.getQuantity() + req.getQuantity();
                return updateCartItemQuantity(cartItem.getId(), newQuantity);
            }
        }
        CartItem newCartItem = new CartItem();
        newCartItem.setFood(food);
        newCartItem.setCart(cart);
        newCartItem.setQuantity(req.getQuantity());
        newCartItem.setIngredients(req.getIngredients());
        newCartItem.setTotalPrice(req.getQuantity() * food.getPrice());
        System.out.println("price" + food.getPrice() + "quantity" + req.getQuantity());
        CartItem savedItem = cartItemRepository.save(newCartItem);
        cart.getItems().add(savedItem);

        return savedItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {

        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
        if (cartItem.isEmpty()) {
            throw new Exception("cart item not found...");
        }
        CartItem item = cartItem.get();
        item.setQuantity(quantity);
        item.setTotalPrice(item.getFood().getPrice() * quantity);

        return cartItemRepository.save(item);
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartRepository.findByCustomerId(user.getId());

        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
        if (cartItem.isEmpty()) {
            throw new Exception("cart item not found...");
        }

        CartItem item = cartItem.get();
        cart.getItems().remove(item);

        return cartRepository.save(cart);
    }

    @Override
    public int calculateCartTotal(Cart cart) throws Exception {

        int total = 0;
        for (CartItem cartItem : cart.getItems()) {
            total += cartItem.getFood().getPrice() * cartItem.getQuantity();
            System.out.println(
                    "itemPrice is $ " + cartItem.getFood().getPrice() + "itemQuantity is N. " + cartItem.getQuantity());
        }
        System.out.println("total is $ " + total);
        return total;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {

        Optional<Cart> optionalCart = cartRepository.findById(id);
        if (optionalCart.isEmpty()) {
            throw new Exception("cart not found with id: " + id);
        }
        return optionalCart.get();
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {
        Cart presentCart = cartRepository.findByCustomerId(userId);
        int total = 0;
        for (CartItem cartItem : presentCart.getItems()) {
            total += cartItem.getTotalPrice();
        }
        presentCart.setTotal(total);
        return presentCart;
    }

    @Override
    public Cart clearCart(Long userId) throws Exception {
        Cart cart = findCartByUserId(userId);
        cart.getItems().clear();
        return cartRepository.save(cart);
    }

}
