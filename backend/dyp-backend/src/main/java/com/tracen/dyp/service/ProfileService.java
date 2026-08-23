package com.tracen.dyp.service;

import org.springframework.stereotype.Service;
import com.tracen.dyp.security.CurrentUserService;
import com.tracen.dyp.dto.CreateProfileRequest;
import com.tracen.dyp.dto.ProfileCompletionResponse;
import com.tracen.dyp.dto.ProfileResponse;
import com.tracen.dyp.entity.Profile;
import com.tracen.dyp.entity.User;
import com.tracen.dyp.repository.ProfileRepository;
import com.tracen.dyp.repository.UserRepository;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final CurrentUserService currentUserService;

    public ProfileService(
            ProfileRepository profileRepository,
            UserRepository userRepository, CurrentUserService currentUserService) {
        this.currentUserService = currentUserService;
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    public ProfileResponse createProfile(
            Long userId,
            CreateProfileRequest request) {
        currentUserService.verifyUserAccess(userId);
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
        currentUserService.verifyUserAccess(userId);
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Profile not found"));

        return toResponse(profile);
    }

    public ProfileResponse updateProfile(
            Long userId,
            CreateProfileRequest request) {
        currentUserService.verifyUserAccess(userId);
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Profile not found"));

        profile.setEducation(request.getEducation());
        profile.setDegree(request.getDegree());
        profile.setGraduationYear(request.getGraduationYear());
        profile.setCurrentSkills(request.getCurrentSkills());
        profile.setWorkExperience(request.getWorkExperience());
        profile.setPreferredIndustries(request.getPreferredIndustries());
        profile.setDreamRoles(request.getDreamRoles());
        profile.setWeeklyLearningHours(request.getWeeklyLearningHours());
        profile.setCareerPriorities(request.getCareerPriorities());
        profile.setPreferredWorkLocation(
                request.getPreferredWorkLocation());

        Profile updatedProfile = profileRepository.save(profile);

        return toResponse(updatedProfile);
    }

    public ProfileCompletionResponse getProfileCompletion(Long userId) {
    currentUserService.verifyUserAccess(userId);
    Profile profile = profileRepository.findByUserId(userId)
            .orElseThrow(() ->
                    new IllegalArgumentException("Profile not found"));

    int completedFields = 0;
    int totalFields = 10;

    if (hasText(profile.getEducation())) completedFields++;
    if (hasText(profile.getDegree())) completedFields++;
    if (profile.getGraduationYear() != null) completedFields++;
    if (hasText(profile.getCurrentSkills())) completedFields++;
    if (hasText(profile.getWorkExperience())) completedFields++;
    if (hasText(profile.getPreferredIndustries())) completedFields++;
    if (hasText(profile.getDreamRoles())) completedFields++;
    if (profile.getWeeklyLearningHours() != null
            && profile.getWeeklyLearningHours() > 0) completedFields++;
    if (hasText(profile.getCareerPriorities())) completedFields++;
    if (hasText(profile.getPreferredWorkLocation())) completedFields++;

    int completionPercentage =
            (completedFields * 100) / totalFields;

    return new ProfileCompletionResponse(
            userId,
            true,
            completionPercentage,
            completionPercentage == 100
    );
    }

    private boolean hasText(String value) {
         return value != null && !value.trim().isEmpty();
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