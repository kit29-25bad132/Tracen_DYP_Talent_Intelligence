package com.tracen.dyp.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserService {

    public Long getCurrentUserId() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        if (authentication == null ||
                !authentication.isAuthenticated()) {

            throw new IllegalStateException(
                    "User is not authenticated");
        }

        Object principal = authentication.getPrincipal();

        if (!(principal instanceof Long)) {
            throw new IllegalStateException(
                    "Invalid authenticated user");
        }

        return (Long) principal;
    }

    public void verifyUserAccess(Long requestedUserId) {

        Long currentUserId = getCurrentUserId();

        if (!currentUserId.equals(requestedUserId)) {
            throw new org.springframework.security.access.AccessDeniedException(
                    "You are not authorized to access this user's data");
        }
    }
}