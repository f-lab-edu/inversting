package com.flab.investing.global.error.exception;

import static com.flab.investing.global.error.exception.constant.ExceptionMessage.NOTFOUND_USER;

public class NotFoundUserIdException extends RuntimeException{

    public NotFoundUserIdException() {
        super(NOTFOUND_USER.getMessage());
    }
}
