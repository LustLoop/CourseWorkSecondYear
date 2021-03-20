package com.example.demo.product;

public class IncorrectProductTypeException extends RuntimeException {
    public IncorrectProductTypeException(String errorMessage) {
        super(errorMessage);
    }
}
