package com.vvt.icommerce.userservice.jwtutils;

import java.io.Serializable;

public class Token implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String token;

    public Token(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

}
