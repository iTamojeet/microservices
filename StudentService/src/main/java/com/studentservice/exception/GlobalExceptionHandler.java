package com.studentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?>
    handleNotFound(ResourceNotFoundException ex) {
        Map<String, Object> body = Map.of(
                "error", "NOT_FOUND",
                "message", ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequest(BadRequestException ex) {
        Map<String, Object> body = Map.of(
                "error", "BAD_REQUEST",
                "message", ex.getMessage()
        );
        return
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneral(Exception ex) {
        Map<String, Object> body = Map.of(
                "error", "INTERNAL_ERROR",
                "message", ex.getMessage()
        );
        return
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?>
    handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors =
                ex.getBindingResult().getFieldErrors().stream()
                        .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return ResponseEntity.badRequest().body(Map.of(
                "error", "VALIDATION_FAILED",
                "errors", errors));
    }
}