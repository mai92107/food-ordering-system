package com.rafa.online_food_ordering.service;

import org.springframework.beans.factory.annotation.Value;

import com.rafa.online_food_ordering.model.Order;
import com.rafa.online_food_ordering.response.PaymentResponse;

public class PaymentServiceImp implements PaymentService {

    @Value("${api.key}")
    private String stripeSecretKey;

    @Override
    public PaymentResponse createPaymentLink(Order order) {
        // 新增Stripe Dependency
        // <dependency>
        // <groupId>com.stripe</groupId>
        // <artifactId>stripe-java</artifactId>
        // <version>20.62.0</version>
        // </dependency>

        // Stripe.apiKey = stripeSecretKey;
        // SessionCreateParams params =
        // SessionCreateParams.builder()
        // .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
        // .setMode(SessionCreateParams.Mode.PAYMENT)
        // .setSuccessUrl("http://localhost:3000/payment/success/",order.getId())
        // .setCancelUrl("http://localhost:3000/payment/failure")
        // .addLineItem(SessionCreateParams.LineItem.builder()
        // .setQuantity(1L).setPriceData(SessionCreateParams.LineItem.priceData.builder()
        // .setCurrency("twd").setUnitAmount((long) order.getTotalPrice())
        // .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
        // .setName("rafaFood").build()).build()).build()).build();

        // Session session = Session.create(params);
        PaymentResponse res = new PaymentResponse();
        // res.setPayment_url(session.getUrl());
        return res;
    }

}
