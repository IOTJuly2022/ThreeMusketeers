package com.cognizant.training.request;

import lombok.Getter;
import lombok.Setter;


public class CartRequest {

    /**
     * Product in the order
     */
    @Getter
    @Setter
    private Long product;

    /**
     * Quantity of the product
     */
    @Getter
    @Setter
    private int count;
}
