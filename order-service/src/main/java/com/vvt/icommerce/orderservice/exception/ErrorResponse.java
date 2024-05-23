package com.vvt.icommerce.orderservice.exception;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse
{
    private String status;
    private String message;
    private List<ErrorItem> errors;

    public ErrorResponse() {
        status = "";
        message = "";
        errors = new ArrayList<ErrorItem>();
    }

    public void addError(ErrorItem error) {
        errors.add(error);
    }
}
