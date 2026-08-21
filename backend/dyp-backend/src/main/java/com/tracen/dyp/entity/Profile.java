package com.tracen.dyp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    private String education;

    private String degree;

    private Integer graduationYear;

    @Column(columnDefinition = "TEXT")
    private String currentSkills;

    @Column(columnDefinition = "TEXT")
    private String workExperience;

    @Column(columnDefinition = "TEXT")
    private String preferredIndustries;

    @Column(columnDefinition = "TEXT")
    private String dreamRoles;

    private Integer weeklyLearningHours;

    @Column(columnDefinition = "TEXT")
    private String careerPriorities;

    private String preferredWorkLocation;

    public Profile() {
    }

    public Profile(
            User user,
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

        this.user = user;
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

    public User getUser() {
        return user;
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