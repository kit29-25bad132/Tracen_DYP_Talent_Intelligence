package com.tracen.dyp.security;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
 * CORS integration tests verifying:
 *
 * A. Requests from the configured frontend origin receive correct CORS response headers.
 * B. OPTIONS preflight for a protected profile endpoint succeeds (200) with proper CORS headers.
 * C. Actual unauthenticated protected requests still return 401 (CORS preflight does not
 *    bypass authentication for actual requests).
 * D. Actual authenticated requests still work (Bearer JWT in Authorization header).
 * E. Cross-user authorization remains 403.
 * F. Existing authentication/security tests continue passing (covered by other test classes).
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CorsIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${dyp.frontend.origin}")
    private String frontendOrigin;

    private record AuthUser(long userId, String token) {
    }

    private AuthUser registerAndLogin() throws Exception {
        String email = "cors-" + System.nanoTime() + "@dyp.com";

        CreateUserRequest create = new CreateUserRequest();
        create.setName("CORS Test User");
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

    // ----- A: CORS response headers for requests from the configured origin -----

    @Test
    void frontendOriginReceivesCorrectCorsHeadersOnSimpleGetRequest() throws Exception {
        AuthUser user = registerAndLogin();

        // Create profile first so GET returns 200
        mockMvc.perform(post("/api/users/" + user.userId() + "/profile")
                        .header("Authorization", "Bearer " + user.token())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fullProfilePayload())))
                .andExpect(status().isOk());

        // For simple GET requests, Spring adds Access-Control-Allow-Origin but not
        // Access-Control-Allow-Methods (that header is only for preflight responses)
        mockMvc.perform(get("/api/users/" + user.userId() + "/profile")
                        .header("Origin", frontendOrigin)
                        .header("Authorization", "Bearer " + user.token()))
                .andExpect(status().isOk())
                .andExpect(header().string("Access-Control-Allow-Origin", frontendOrigin));
    }

    @Test
    void frontendOriginReceivesCorrectCorsHeadersOnPostRequest() throws Exception {
        AuthUser user = registerAndLogin();

        mockMvc.perform(post("/api/users/" + user.userId() + "/profile")
                        .header("Origin", frontendOrigin)
                        .header("Authorization", "Bearer " + user.token())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fullProfilePayload())))
                .andExpect(status().isOk())
                .andExpect(header().string("Access-Control-Allow-Origin", frontendOrigin));
    }

    // ----- B: OPTIONS preflight succeeds for protected endpoint -----

    @Test
    void optionsPreflightForProtectedProfileEndpointSucceeds() throws Exception {
        AuthUser user = registerAndLogin();

        mockMvc.perform(options("/api/users/" + user.userId() + "/profile")
                        .header("Origin", frontendOrigin)
                        .header("Access-Control-Request-Method", "GET")
                        .header("Access-Control-Request-Headers", "Authorization, Content-Type"))
                .andExpect(status().isOk())
                .andExpect(header().string("Access-Control-Allow-Origin", frontendOrigin))
                .andExpect(header().string("Access-Control-Allow-Methods",
                        "GET,POST,PUT,PATCH,DELETE,OPTIONS"))
                .andExpect(header().string("Access-Control-Allow-Headers",
                        "Authorization, Content-Type"));
    }

    @Test
    void optionsPreflightForProtectedEndpointDoesNotRequireAuthentication() throws Exception {
        // Preflight requests should succeed without authentication, but the actual
        // request must still require it. This test confirms CORS handles preflight
        // independently of the auth filter chain.
        mockMvc.perform(options("/api/users/999/profile")
                        .header("Origin", frontendOrigin)
                        .header("Access-Control-Request-Method", "GET")
                        .header("Access-Control-Request-Headers", "Authorization, Content-Type"))
                .andExpect(status().isOk())
                .andExpect(header().string("Access-Control-Allow-Origin", frontendOrigin));
    }

    // ----- C: Unauthenticated protected requests still return 401 -----

    @Test
    void unauthenticatedRequestToProtectedEndpointStillReturns401() throws Exception {
        AuthUser user = registerAndLogin();

        mockMvc.perform(get("/api/users/" + user.userId() + "/profile")
                        .header("Origin", frontendOrigin))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.status").value(401))
                .andExpect(jsonPath("$.message").isNotEmpty());
    }

    @Test
    void requestWithInvalidTokenStillReturns401() throws Exception {
        AuthUser user = registerAndLogin();

        mockMvc.perform(get("/api/users/" + user.userId() + "/profile")
                        .header("Origin", frontendOrigin)
                        .header("Authorization", "Bearer not-a-real-token"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.status").value(401));
    }

    // ----- D: Authenticated requests still work -----

    @Test
    void authenticatedRequestWithBearerTokenStillWorks() throws Exception {
        AuthUser user = registerAndLogin();

        mockMvc.perform(post("/api/users/" + user.userId() + "/profile")
                        .header("Origin", frontendOrigin)
                        .header("Authorization", "Bearer " + user.token())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fullProfilePayload())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(user.userId()));
    }

    // ----- E: Cross-user authorization remains 403 -----

    @Test
    void crossUserAccessStillReturns403() throws Exception {
        AuthUser owner = registerAndLogin();
        AuthUser attacker = registerAndLogin();

        mockMvc.perform(post("/api/users/" + owner.userId() + "/profile")
                        .header("Authorization", "Bearer " + owner.token())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fullProfilePayload())))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/users/" + owner.userId() + "/profile")
                        .header("Origin", frontendOrigin)
                        .header("Authorization", "Bearer " + attacker.token()))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.status").value(403));
    }

    // ----- F: Origin not matching configured frontend origin is rejected -----

    @Test
    void nonMatchingOriginIsRejected() throws Exception {
        AuthUser user = registerAndLogin();

        // Create profile first
        mockMvc.perform(post("/api/users/" + user.userId() + "/profile")
                        .header("Authorization", "Bearer " + user.token())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fullProfilePayload())))
                .andExpect(status().isOk());

        // Request from non-matching origin is rejected (403) because CORS policy
        // does not allow it. No CORS headers are returned.
        mockMvc.perform(get("/api/users/" + user.userId() + "/profile")
                        .header("Origin", "http://evil.example.com")
                        .header("Authorization", "Bearer " + user.token()))
                .andExpect(status().isForbidden())
                .andExpect(header().doesNotExist("Access-Control-Allow-Origin"));
    }
}
