package com.rafa.online_food_ordering.request;

import java.util.List;

import lombok.Data;

@Data
public class AddCartItemRequest {

    private Long foodId;
    private int quantity;
    private List<String> ingredients; // 假設 ingredients 是字串陣列

}
