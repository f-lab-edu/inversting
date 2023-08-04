package com.flab.investing.global.error;

import com.flab.investing.global.error.constant.ExceptionCode;
import com.flab.investing.global.error.dto.ExceptionResponse;
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
                .map(error -> error.getField() + ":" + error.getDefaultMessage())
                .collect(Collectors.joining("\n ,"));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponse(ExceptionCode.BAD_REQUEST.getCode(), message));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> customException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionResponse(ExceptionCode.CUSTOM_EXCEPTION.getCode(), e.getMessage()));
    }
}
