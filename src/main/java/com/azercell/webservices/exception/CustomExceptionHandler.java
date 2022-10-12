package com.azercell.webservices.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleExceptions(Exception ex, WebRequest request) {
        var exceptionResponse
                = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(exceptionResponse);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        var exceptionResponse
                = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND.value())
                .body(exceptionResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        var response = new ExceptionResponse(LocalDateTime.now(),
                "Validation failed",
                ex.getBindingResult().getAllErrors().toString());

        return ResponseEntity
                .badRequest()
                .body(response);
    }
}
