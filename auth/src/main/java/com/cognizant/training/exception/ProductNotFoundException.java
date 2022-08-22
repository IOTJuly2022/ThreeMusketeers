package com.cognizant.training.exception;


public class ProductNotFoundException extends RuntimeException {
    // Only here to prevent compiler warning
    private static final long serialVersionUID = 3537154583187107896L;

    public ProductNotFoundException(Long id) {
        super("Could not find Product of ID:: " + id);
    }
}
