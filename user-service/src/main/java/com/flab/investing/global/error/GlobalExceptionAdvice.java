package com.flab.investing.global.error;

import com.flab.investing.global.error.constant.ExceptionCode;
import com.flab.investing.global.error.dto.ExceptionResponse;
import com.flab.investing.global.error.exception.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> validateException(MethodArgumentNotValidException e) {
        String message = e.getFieldErrors().stream()
                .map(error -> error.getField() + "은(는)" + error.getDefaultMessage())
                .findFirst()
                .orElseGet(() -> "");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponse(ExceptionCode.BAD_REQUEST.getCode(), message));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> customException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionResponse(ExceptionCode.CUSTOM_EXCEPTION.getCode(), e.getMessage()));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ExceptionResponse> authenticationException(AuthenticationException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ExceptionResponse(exception.getCode(), exception.getMessage() ));
    }

}
