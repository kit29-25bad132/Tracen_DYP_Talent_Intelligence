package com.tracen.dyp.service;

import org.springframework.stereotype.Service;

import com.tracen.dyp.dto.CreateProfileRequest;
import com.tracen.dyp.dto.ProfileResponse;
import com.tracen.dyp.entity.Profile;
import com.tracen.dyp.entity.User;
import com.tracen.dyp.repository.ProfileRepository;
import com.tracen.dyp.repository.UserRepository;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public ProfileService(
            ProfileRepository profileRepository,
            UserRepository userRepository) {

        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    public ProfileResponse createProfile(
            Long userId,
            CreateProfileRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found"));

        if (profileRepository.existsByUserId(userId)) {
            throw new IllegalArgumentException(
                    "Profile already exists for this user");
        }

        Profile profile = new Profile(
                user,
                request.getEducation(),
                request.getDegree(),
                request.getGraduationYear(),
                request.getCurrentSkills(),
                request.getWorkExperience(),
                request.getPreferredIndustries(),
                request.getDreamRoles(),
                request.getWeeklyLearningHours(),
                request.getCareerPriorities(),
                request.getPreferredWorkLocation()
        );

        Profile savedProfile = profileRepository.save(profile);

        return toResponse(savedProfile);
    }

    public ProfileResponse getProfile(Long userId) {

        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Profile not found"));

        return toResponse(profile);
    }

    private ProfileResponse toResponse(Profile profile) {

        return new ProfileResponse(
                profile.getId(),
                profile.getUser().getId(),
                profile.getEducation(),
                profile.getDegree(),
                profile.getGraduationYear(),
                profile.getCurrentSkills(),
                profile.getWorkExperience(),
                profile.getPreferredIndustries(),
                profile.getDreamRoles(),
                profile.getWeeklyLearningHours(),
                profile.getCareerPriorities(),
                profile.getPreferredWorkLocation()
        );
    }
}