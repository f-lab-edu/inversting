package com.flab.investing.stock.batch.domain;

import lombok.Getter;

@Getter
public class Token {

    private final String accessToken;

    public Token(String accessToken) {
        this.accessToken = accessToken;
    }
}
