package com.vvt.icommerce.paymentservice.exception;

public class NotEnoughFundException extends RuntimeException{
    public NotEnoughFundException(String message) {
        super(message);
    }
}
