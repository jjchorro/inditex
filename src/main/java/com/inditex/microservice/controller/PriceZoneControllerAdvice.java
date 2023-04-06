package com.inditex.microservice.controller;

import com.inditex.microservice.domain.response.ErrorDto;
import com.inditex.microservice.exception.PriceZoneException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class PriceZoneControllerAdvice {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity<ErrorDto> handleHttpRequestMethodNotSupportedException(
        final HttpRequestMethodNotSupportedException e) {
        return buildErrorResponse(HttpStatus.METHOD_NOT_ALLOWED, e.getMessage());
    }

    @ExceptionHandler(PriceZoneException.class)
    public ResponseEntity<ErrorDto> handlePriceZoneException(final PriceZoneException e) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDto> handleIllegalArgumentException(final IllegalArgumentException e) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDto> handleBindException(final BindException e) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    public static ResponseEntity<ErrorDto> buildErrorResponse(final HttpStatus errorCode, final String errorMessage) {
        log.error(errorMessage);
        final ErrorDto errorDto = new ErrorDto(Integer.toString(errorCode.value()), errorMessage);
        return ResponseEntity.status(errorCode).body(errorDto);
    }
}
