package com.tracen.dyp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tracen.dyp.dto.CreateProfileRequest;
import com.tracen.dyp.dto.CreateUserRequest;
import com.tracen.dyp.dto.LoginRequest;
import com.tracen.dyp.exception.ConflictException;
import com.tracen.dyp.exception.InvalidCredentialsException;
import com.tracen.dyp.exception.ResourceNotFoundException;
import com.tracen.dyp.repository.ProfileRepository;
import com.tracen.dyp.repository.UserRepository;
import com.tracen.dyp.entity.User;
import com.tracen.dyp.security.CurrentUserService;
import com.tracen.dyp.security.JwtService;

/**
 * Focused tests for the 404 vs 409 exception semantics introduced in
 * {@link ResourceNotFoundException} and {@link ConflictException}:
 *
 * nonexistent user     -> ResourceNotFoundException (404)
 * nonexistent profile  -> ResourceNotFoundException (404)
 * duplicate user email -> ConflictException          (409)
 * duplicate profile    -> ConflictException          (409)
 */
class ServiceExceptionMappingTest {

    private UserRepository userRepository;
    private ProfileRepository profileRepository;
    private UserService userService;
    private ProfileService profileService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        JwtService jwtService = mock(JwtService.class);
        userService = new UserService(userRepository, passwordEncoder, jwtService);

        profileRepository = mock(ProfileRepository.class);
        CurrentUserService currentUserService = mock(CurrentUserService.class);
        profileService = new ProfileService(
                profileRepository, userRepository, currentUserService);
    }

    // ----- 404: nonexistent user -----

    @Test
    void createProfileForNonexistentUserThrowsResourceNotFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        CreateProfileRequest request = new CreateProfileRequest();

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> profileService.createProfile(99L, request));

        assertEquals("User not found", exception.getMessage());
    }

    // ----- 404: nonexistent profile -----

    @Test
    void getProfileForNonexistentProfileThrowsResourceNotFound() {
        when(profileRepository.findByUserId(42L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> profileService.getProfile(42L));

        assertEquals("Profile not found", exception.getMessage());
    }

    @Test
    void updateProfileForNonexistentProfileThrowsResourceNotFound() {
        when(profileRepository.findByUserId(42L)).thenReturn(Optional.empty());

        CreateProfileRequest request = new CreateProfileRequest();

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> profileService.updateProfile(42L, request));

        assertEquals("Profile not found", exception.getMessage());
    }

    @Test
    void getProfileCompletionForNonexistentProfileThrowsResourceNotFound() {
        when(profileRepository.findByUserId(42L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> profileService.getProfileCompletion(42L));

        assertEquals("Profile not found", exception.getMessage());
    }

    // ----- 409: duplicates -----

    @Test
    void registerDuplicateEmailThrowsConflict() {
        when(userRepository.existsByEmail("dup@dyp.com")).thenReturn(true);

        CreateUserRequest request = new CreateUserRequest();
        request.setName("Test User");
        request.setEmail("dup@dyp.com");
        request.setPassword("password123");

        ConflictException exception = assertThrows(
                ConflictException.class,
                () -> userService.createUser(request));

        assertEquals("Email already registered", exception.getMessage());
    }

    @Test
    void createDuplicateProfileThrowsConflict() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(
                new User("Test User", "test@dyp.com", "hashed")));
        when(profileRepository.existsByUserId(1L)).thenReturn(true);

        CreateProfileRequest request = new CreateProfileRequest();

        ConflictException exception = assertThrows(
                ConflictException.class,
                () -> profileService.createProfile(1L, request));

        assertEquals("Profile already exists for this user",
                exception.getMessage());
    }

    // ----- 401 behavior must not regress -----

    @Test
    void loginWithUnknownEmailStillThrowsInvalidCredentials() {
        when(userRepository.findByEmail("missing@dyp.com"))
                .thenReturn(Optional.empty());

        LoginRequest request = new LoginRequest();
        request.setEmail("missing@dyp.com");
        request.setPassword("password123");

        assertThrows(InvalidCredentialsException.class,
                () -> userService.login(request));
    }
}
