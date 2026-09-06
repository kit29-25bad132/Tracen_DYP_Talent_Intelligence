package com.tracen.dyp.exception;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleValidationException(
            MethodArgumentNotValidException exception) {

        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(error.getField(), error.getDefaultMessage())
                );

        Map<String, Object> response = new HashMap<>();

        response.put("status", 400);
        response.put("message", "Validation failed");
        response.put("errors", errors);

        return response;
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleResourceNotFound(
            ResourceNotFoundException exception) {

        Map<String, Object> response = new HashMap<>();
        response.put("status", 404);
        response.put("message", exception.getMessage());

        return response;
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, Object> handleConflict(
            ConflictException exception) {

        Map<String, Object> response = new HashMap<>();
        response.put("status", 409);
        response.put("message", exception.getMessage());

        return response;
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Map<String, Object> handleAccessDenied(
            AccessDeniedException exception) {

        Map<String, Object> response = new HashMap<>();
        response.put("status", 403);
        response.put("message",
                "You are not authorized to access this resource");

        return response;
    }

    @ExceptionHandler(IllegalArgumentException.class)
@ResponseStatus(HttpStatus.CONFLICT)
public Map<String, Object> handleIllegalArgumentException(
        IllegalArgumentException exception) {

    Map<String, Object> response = new HashMap<>();
    response.put("status", 409);
    response.put("message", exception.getMessage());

    return response;
}

@ExceptionHandler(InvalidCredentialsException.class)
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public Map<String, Object> handleInvalidCredentials(
        InvalidCredentialsException exception) {

    Map<String, Object> response = new HashMap<>();

    response.put("status", 401);
    response.put("message", exception.getMessage());

    return response;
}
}