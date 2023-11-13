package com.flab.investing.global.error;

import com.flab.investing.global.error.dto.ExceptionResponse;
import com.flab.investing.global.error.exception.constant.ExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    private static final String ERROR_CODE = "9999";

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> customException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionResponse(ERROR_CODE, e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> methodValidException(MethodArgumentNotValidException exception) {
        String message = makErrorResponse(exception);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponse(ERROR_CODE, message));
    }

    private String makErrorResponse(final BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return bindingResult.getFieldError().getDefaultMessage();
        }

        return ExceptionMessage.INVALID_PARAMETER.getMessage();
    }

}
