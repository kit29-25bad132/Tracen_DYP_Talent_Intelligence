package com.tracen.dyp.dto;

public class ProfileResponse {

    private Long id;
    private Long userId;
    private String education;
    private String degree;
    private Integer graduationYear;
    private String currentSkills;
    private String workExperience;
    private String preferredIndustries;
    private String dreamRoles;
    private Integer weeklyLearningHours;
    private String careerPriorities;
    private String preferredWorkLocation;

    public ProfileResponse(
            Long id,
            Long userId,
            String education,
            String degree,
            Integer graduationYear,
            String currentSkills,
            String workExperience,
            String preferredIndustries,
            String dreamRoles,
            Integer weeklyLearningHours,
            String careerPriorities,
            String preferredWorkLocation) {

        this.id = id;
        this.userId = userId;
        this.education = education;
        this.degree = degree;
        this.graduationYear = graduationYear;
        this.currentSkills = currentSkills;
        this.workExperience = workExperience;
        this.preferredIndustries = preferredIndustries;
        this.dreamRoles = dreamRoles;
        this.weeklyLearningHours = weeklyLearningHours;
        this.careerPriorities = careerPriorities;
        this.preferredWorkLocation = preferredWorkLocation;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
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
}