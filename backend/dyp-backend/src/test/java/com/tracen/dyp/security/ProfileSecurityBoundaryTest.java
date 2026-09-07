package com.tracen.dyp.security;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import tools.jackson.databind.ObjectMapper;
import com.tracen.dyp.dto.CreateProfileRequest;
import com.tracen.dyp.dto.CreateUserRequest;
import com.tracen.dyp.dto.LoginRequest;

/**
 * Security-boundary tests exercising the real HTTP + Spring Security filter
 * chain with real registration, real login, and real JWTs (no mocking of the
 * security layer):
 *
 * A. Authenticated user can create and read their own profile.
 * B. Authenticated user cannot access/update another user's profile (403).
 * C. Unauthenticated / invalid-token access to a protected profile endpoint
 *    is rejected with 401.
 * D. Existing valid JWT authentication remains functional (login issues a
 *    token that authorizes protected requests).
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ProfileSecurityBoundaryTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private record AuthUser(long userId, String token) {
    }

    private AuthUser registerAndLogin() throws Exception {
        String email = "sec-" + System.nanoTime() + "@dyp.com";

        CreateUserRequest create = new CreateUserRequest();
        create.setName("Security Test User");
        create.setEmail(email);
        create.setPassword("password123");

        MvcResult created = mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(create)))
                .andExpect(status().isOk())
                .andReturn();

        long userId = objectMapper.readTree(
                created.getResponse().getContentAsString()).get("id").asLong();

        LoginRequest login = new LoginRequest();
        login.setEmail(email);
        login.setPassword("password123");

        MvcResult loginResult = mockMvc.perform(post("/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(login)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty())
                .andReturn();

        String token = objectMapper.readTree(
                loginResult.getResponse().getContentAsString()).get("token").asText();

        return new AuthUser(userId, token);
    }

    private CreateProfileRequest fullProfilePayload() {
        CreateProfileRequest request = new CreateProfileRequest();
        request.setEducation("B.Tech");
        request.setDegree("Computer Science");
        request.setGraduationYear(2029);
        request.setCurrentSkills("Java, SQL");
        request.setWorkExperience("Student projects");
        request.setPreferredIndustries("Software");
        request.setDreamRoles("Backend Engineer");
        request.setWeeklyLearningHours(20);
        request.setCareerPriorities("Learning, Growth");
        request.setPreferredWorkLocation("Remote");
        return request;
    }

    // ----- A + D: own-profile access with a real JWT -----

    @Test
    void authenticatedUserCanCreateOwnProfile() throws Exception {
        AuthUser user = registerAndLogin();

        mockMvc.perform(post("/api/users/" + user.userId() + "/profile")
                        .header("Authorization", "Bearer " + user.token())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fullProfilePayload())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(user.userId()));
    }

    @Test
    void authenticatedUserCanGetOwnProfile() throws Exception {
        AuthUser user = registerAndLogin();

        mockMvc.perform(post("/api/users/" + user.userId() + "/profile")
                        .header("Authorization", "Bearer " + user.token())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fullProfilePayload())))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/users/" + user.userId() + "/profile")
                        .header("Authorization", "Bearer " + user.token()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(user.userId()))
                .andExpect(jsonPath("$.education").value("B.Tech"));
    }

    // ----- B: cross-user access must be denied with JSON 403 -----

    @Test
    void authenticatedUserCannotAccessAnotherUsersProfile() throws Exception {
        AuthUser owner = registerAndLogin();
        AuthUser attacker = registerAndLogin();

        // Owner creates their profile first, so the attacker's request proves
        // that an existing other-user resource is protected (not a 404 path).
        mockMvc.perform(post("/api/users/" + owner.userId() + "/profile")
                        .header("Authorization", "Bearer " + owner.token())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fullProfilePayload())))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/users/" + owner.userId() + "/profile")
                        .header("Authorization", "Bearer " + attacker.token()))
                .andExpect(status().isForbidden())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(403))
                .andExpect(jsonPath("$.message").isNotEmpty());
    }

    @Test
    void authenticatedUserCannotUpdateAnotherUsersProfile() throws Exception {
        AuthUser owner = registerAndLogin();
        AuthUser attacker = registerAndLogin();

        mockMvc.perform(put("/api/users/" + owner.userId() + "/profile")
                        .header("Authorization", "Bearer " + attacker.token())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fullProfilePayload())))
                .andExpect(status().isForbidden())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(403))
                .andExpect(jsonPath("$.message").isNotEmpty());
    }

    // ----- C: unauthenticated / invalid token must be 401 -----

    @Test
    void unauthenticatedRequestToProtectedProfileEndpointIs401() throws Exception {
        AuthUser user = registerAndLogin();

        mockMvc.perform(get("/api/users/" + user.userId() + "/profile"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(401))
                .andExpect(jsonPath("$.message").isNotEmpty());
    }

    @Test
    void requestWithInvalidTokenToProtectedProfileEndpointIs401() throws Exception {
        AuthUser user = registerAndLogin();

        mockMvc.perform(get("/api/users/" + user.userId() + "/profile")
                        .header("Authorization", "Bearer not-a-real-jwt-token"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(401))
                .andExpect(jsonPath("$.message").isNotEmpty());
    }
}
