package com.tracen.dyp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tracen.dyp.dto.CreateProfileRequest;
import com.tracen.dyp.dto.ProfileCompletionResponse;
import com.tracen.dyp.dto.ProfileResponse;
import com.tracen.dyp.service.ProfileService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users/{userId}/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public ProfileResponse createProfile(
            @PathVariable Long userId,
            @Valid @RequestBody CreateProfileRequest request) {

        return profileService.createProfile(userId, request);
    }

    @GetMapping
    public ProfileResponse getProfile(
            @PathVariable Long userId) {

        return profileService.getProfile(userId);
    }

    @GetMapping("/completion")
     public ProfileCompletionResponse getProfileCompletion(
        @PathVariable Long userId) {

         return profileService.getProfileCompletion(userId);
    }

    @PutMapping
    public ProfileResponse updateProfile(
            @PathVariable Long userId,
            @Valid @RequestBody CreateProfileRequest request) {

        return profileService.updateProfile(userId, request);
    }
}