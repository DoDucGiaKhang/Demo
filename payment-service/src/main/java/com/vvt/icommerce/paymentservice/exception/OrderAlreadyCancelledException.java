package com.vvt.icommerce.paymentservice.exception;

public class OrderAlreadyCancelledException extends RuntimeException{
    public OrderAlreadyCancelledException(String message) {
        super(message);
    }
}
