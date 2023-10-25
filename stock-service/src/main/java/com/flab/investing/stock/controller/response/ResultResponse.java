package com.flab.investing.stock.controller.response;

public record ResultResponse<T> (
    String code,
    String message,
    T data
){
}
