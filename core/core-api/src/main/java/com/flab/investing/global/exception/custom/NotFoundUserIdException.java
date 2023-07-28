package com.flab.investing.global.exception.custom;

public class NotFoundUserIdException extends RuntimeException{

    public NotFoundUserIdException() {
        super("유저아이디를 찾을 수 없습니다.");
    }
}
