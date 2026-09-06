package com.tracen.dyp.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;

import tools.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;

/**
 * Writes the application's standard JSON error structure
 * ({@code { "status": ..., "message": ... }}) to an HTTP response.
 *
 * Used by Spring Security response components so that authentication and
 * authorization failures match the JSON shape produced by
 * GlobalExceptionHandler. Never exposes stack traces, JWT contents, or any
 * other sensitive information.
 */
public final class ErrorJsonWriter {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private ErrorJsonWriter() {
    }

    public static void write(
            HttpServletResponse response,
            int status,
            String message) throws IOException {

        response.setStatus(status);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Map<String, Object> body = new HashMap<>();
        body.put("status", status);
        body.put("message", message);

        OBJECT_MAPPER.writeValue(response.getWriter(), body);
    }
}
