package com.tracen.dyp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateProfileRequest {

    @NotBlank
    private String education;

    @NotBlank
    private String degree;

    @NotNull
    private Integer graduationYear;

    private String currentSkills;

    private String workExperience;

    private String preferredIndustries;

    private String dreamRoles;

    @NotNull
    @Min(1)
    private Integer weeklyLearningHours;

    private String careerPriorities;

    private String preferredWorkLocation;

    public CreateProfileRequest() {
    }

    public String getEducation() {
        return education;
    }

    public String getDegree() {
        return degree;
    }

    public Integer getGraduationYear() {
        return graduationYear;
    }

    public String getCurrentSkills() {
        return currentSkills;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public String getPreferredIndustries() {
        return preferredIndustries;
    }

    public String getDreamRoles() {
        return dreamRoles;
    }

    public Integer getWeeklyLearningHours() {
        return weeklyLearningHours;
    }

    public String getCareerPriorities() {
        return careerPriorities;
    }

    public String getPreferredWorkLocation() {
        return preferredWorkLocation;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setGraduationYear(Integer graduationYear) {
        this.graduationYear = graduationYear;
    }

    public void setCurrentSkills(String currentSkills) {
        this.currentSkills = currentSkills;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public void setPreferredIndustries(String preferredIndustries) {
        this.preferredIndustries = preferredIndustries;
    }

    public void setDreamRoles(String dreamRoles) {
        this.dreamRoles = dreamRoles;
    }

    public void setWeeklyLearningHours(Integer weeklyLearningHours) {
        this.weeklyLearningHours = weeklyLearningHours;
    }

    public void setCareerPriorities(String careerPriorities) {
        this.careerPriorities = careerPriorities;
    }

    public void setPreferredWorkLocation(String preferredWorkLocation) {
        this.preferredWorkLocation = preferredWorkLocation;
    }
}