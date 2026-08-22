package com.tracen.dyp.dto;

public class ProfileCompletionResponse {

    private Long userId;
    private boolean profileExists;
    private int completionPercentage;
    private boolean completed;

    public ProfileCompletionResponse(
            Long userId,
            boolean profileExists,
            int completionPercentage,
            boolean completed) {

        this.userId = userId;
        this.profileExists = profileExists;
        this.completionPercentage = completionPercentage;
        this.completed = completed;
    }

    public Long getUserId() {
        return userId;
    }

    public boolean isProfileExists() {
        return profileExists;
    }

    public int getCompletionPercentage() {
        return completionPercentage;
    }

    public boolean isCompleted() {
        return completed;
    }
}