package com.tracen.dyp.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * Verifies that GlobalExceptionHandler maps the new domain exceptions to the
 * correct HTTP status codes while preserving the existing JSON body shape,
 * and that the legacy IllegalArgumentException fallback (409) is unchanged.
 */
class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void resourceNotFoundMapsTo404() {
        Map<String, Object> body = handler.handleResourceNotFound(
                new ResourceNotFoundException("Profile not found"));

        assertEquals(404, body.get("status"));
        assertEquals("Profile not found", body.get("message"));
    }

    @Test
    void conflictMapsTo409() {
        Map<String, Object> body = handler.handleConflict(
                new ConflictException("Email already registered"));

        assertEquals(409, body.get("status"));
        assertEquals("Email already registered", body.get("message"));
    }

    @Test
    void illegalArgumentFallbackStillMapsTo409() {
        Map<String, Object> body = handler.handleIllegalArgumentException(
                new IllegalArgumentException("legacy fallback"));

        assertEquals(409, body.get("status"));
        assertEquals("legacy fallback", body.get("message"));
    }
}
