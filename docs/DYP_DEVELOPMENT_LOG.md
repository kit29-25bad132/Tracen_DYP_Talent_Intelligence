# DYP Talent Intelligence — Development Log

> **Project:** TRACEN DYP — Talent Intelligence Platform  
> **DYP:** Discover Your Potential  
> **Development Period:** Day 1 → Day 10
> **Current Checkpoint:** Phase 4 — Profile & Goal Discovery — **COMPLETED**
> **Latest Git Checkpoint:** Phase 4 completion + security verification

---

# Day 1 — Backend Environment + First User Flow

## Environment Setup

### PostgreSQL

- PostgreSQL 18.x
- Host: `localhost`
- Port: `5432`
- Username: `postgres`
- Database: `dyp_db`
- pgAdmin configured
- `psql` added to Windows PATH

### Database Security

The PostgreSQL password is **not stored in Git**.

Spring Boot reads it from:

```properties
spring.datasource.password=${DB_PASSWORD}
```

The password is supplied through the `DB_PASSWORD` environment variable.

## Spring Boot Backend

Established the initial backend using:

- Java
- Spring Boot
- Maven
- Spring Data JPA
- Hibernate
- PostgreSQL

Initial layered structure:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
PostgreSQL
```

## First User Flow

Implemented the initial user persistence flow:

- User entity
- User repository
- User service
- User controller
- DTO-based API
- PostgreSQL persistence

### API

```http
POST /api/users
```

## Day 1 Result

```text
PostgreSQL Setup       ✅
Spring Boot Setup      ✅
Maven Setup             ✅
Database Connection     ✅
Backend Structure       ✅
User Persistence        ✅
Git/GitHub Setup        ✅
```

---

# Day 2 — Backend Foundation

## Backend Layering

Expanded the backend foundation into a clear layered architecture:

```text
Controller
    ↓
DTO
    ↓
Service
    ↓
Repository
    ↓
Entity
    ↓
PostgreSQL
```

## Core Backend Components

Established the foundation for:

- Entities
- Repositories
- Services
- Controllers
- DTOs
- Validation
- Exception handling

## Development Principle

Every feature follows:

```text
Requirement
    ↓
Entity
    ↓
Repository
    ↓
DTO
    ↓
Service
    ↓
Controller
    ↓
Database
    ↓
API Testing
```

## Day 2 Result

The backend foundation became structured enough to support the later authentication and profile workflows.

---

# Day 3 — User Registration Foundation

## Registration Workflow

Implemented the user registration flow around the `User` domain.

```text
Registration Request
        ↓
CreateUserRequest
        ↓
UserService
        ↓
UserRepository
        ↓
PostgreSQL
        ↓
UserResponse
```

## Registration Requirements

Implemented / addressed:

- User creation
- Request DTO
- Response DTO
- Email handling
- Persistence
- API-level validation
- Duplicate-user handling foundation

### API

```http
POST /api/users
```

## Verification

Tested the REST API against the local Spring Boot backend and PostgreSQL database.

---

# Day 4 — Authentication

## Password Security

Introduced password hashing rather than storing plaintext passwords.

```text
Plain Password
      ↓
BCrypt
      ↓
Password Hash
      ↓
PostgreSQL
```

Password hashes are persisted instead of raw passwords.

## Login Flow

Implemented:

```text
Login Request
      ↓
User Lookup
      ↓
Password Verification
      ↓
Login Response
```

### API

```http
POST /api/users/login
```

## Error Handling

Implemented handling for:

- Duplicate email
- Invalid credentials
- Authentication failures

A dedicated `InvalidCredentialsException` was introduced.

## Testing

The authentication API was tested using REST requests / PowerShell.

---

# Day 5 — Backend Verification + Error Handling

## Backend Verification

Continued testing the complete user flow:

```text
Register
   ↓
Persist User
   ↓
Login
   ↓
Verify Password
```

## Exception Handling

Established centralized exception handling through:

```text
GlobalExceptionHandler
```

This keeps API errors consistent rather than allowing raw exceptions to leak to clients.

## API Testing

Used PowerShell / REST requests to verify:

- User creation
- Duplicate email behavior
- Login
- Invalid password handling
- Database persistence

## Build Verification

The project was repeatedly compiled and run through Maven / Spring Boot.

---

# Day 6 — Profile Foundation

## Phase 4 Started

Development moved from authentication into:

> **Phase 4 — Profile & Goal Discovery**

The purpose of Phase 4 is to capture the student's contextual information before assessment planning.

## Profile Entity

Introduced the `Profile` entity and its relationship with `User`.

```text
User
 │
 │ 1 : 1
 ▼
Profile
```

## Profile Data

The profile model covers the required student context:

- Education
- Degree
- Graduation year
- Current skills
- Work experience
- Preferred industries
- Dream roles
- Weekly learning hours
- Career priorities
- Preferred work location

## Repository

Added:

```text
ProfileRepository
```

## Service

Added:

```text
ProfileService
```

The service contains profile creation / retrieval business logic.

---

# Day 7 — Profile API

## DTOs

Introduced:

```text
CreateProfileRequest
ProfileResponse
```

The DTO boundary prevents the database entity from becoming the public API contract.

## Controller

Introduced:

```text
ProfileController
```

## Profile Creation API

```http
POST /api/users/{userId}/profile
```

Example request:

```json
{
  "education": "B.Tech",
  "degree": "Artificial Intelligence and Data Science",
  "graduationYear": 2029,
  "currentSkills": "Java, Python, C, SQL",
  "workExperience": "Student projects and hackathons",
  "preferredIndustries": "Software, AI, Technology",
  "dreamRoles": "Software Engineer, Backend Engineer",
  "weeklyLearningHours": 20,
  "careerPriorities": "Learning, Salary, Growth",
  "preferredWorkLocation": "India"
}
```

## Profile Retrieval API

```http
GET /api/users/{userId}/profile
```

## Layered Flow

```text
HTTP Request
     ↓
ProfileController
     ↓
ProfileService
     ↓
ProfileRepository
     ↓
PostgreSQL
```

---

# Day 8 — Profile Persistence + Completion Work

## PostgreSQL Verification

Verified that profile data could be persisted and retrieved through the Spring Boot backend.

The profile workflow reached the database successfully.

```text
User
  ↓
Profile API
  ↓
Profile Service
  ↓
Profile Repository
  ↓
PostgreSQL
```

## Profile Completion

Profile completion tracking was introduced as part of the Phase 4 workflow.

The intended direction is to determine whether the required profile context has been supplied before allowing the system to proceed deeper into DYP's assessment workflow.

## Architecture / Documentation

Updated project architecture documentation to reflect:

- Modular monolith
- Backend layering
- Profile workflow
- Future intelligence engines
- DYP's long-term workflow

## Phase 4 Status at Day 8

Profile backend was functional, but the phase was **not yet considered complete**.

Remaining work included:

- Profile update workflow
- Profile completion workflow
- Full authorization verification
- Full API verification

---

# Day 9 — Phase 4 Continuation + WIP Checkpoint

## Current Phase

> **Phase 4 — Profile & Goal Discovery**

## Current Work

Continued the remaining Profile workflow rather than moving prematurely to Assessment Planning.

The implementation contained:

```text
Profile Entity
Profile Repository
CreateProfileRequest
ProfileResponse
ProfileService
ProfileController
Create Profile API
Get Profile API
PostgreSQL Persistence
```

## Git Checkpoint

The repository was maintained as a WIP checkpoint before final Phase 4 verification.

The development rule remained:

```text
Finish Phase
     ↓
Compile
     ↓
Run
     ↓
API Test
     ↓
Verify Requirements
     ↓
Commit as Complete
     ↓
Push
     ↓
Next Phase
```

Therefore, Phase 4 was not marked complete until the remaining requirements were implemented and verified.

---

# Day 10 — Phase 4 Completion + Security Verification

## Phase

> **Phase 4 — Profile & Goal Discovery — COMPLETED**

Phase 4 was completed on the backend after implementing and verifying the remaining profile workflow and authorization requirements.

The Phase 4 implementation now provides the student context required before the DYP assessment-planning stage.

---

## Completed Profile Workflow

The profile workflow now supports:

```text
User
  ↓
Authentication
  ↓
Profile Creation
  ↓
Profile Retrieval
  ↓
Profile Update
  ↓
Profile Completion
  ↓
Assessment Planning
```

---

## Authentication + JWT Security Verification

JWT authentication was integrated into the protected profile workflow.

The backend uses:

```text
Login
  ↓
JWT Generation
  ↓
Bearer Token
  ↓
JWT Authentication Filter
  ↓
SecurityContext
  ↓
CurrentUserService
  ↓
User Access Verification
  ↓
Protected Profile API
```

### JWT Secret Configuration

The JWT signing secret was externalized rather than hardcoded.

The application reads the secret through configuration:

```text
dyp.security.jwt-secret
```

The development environment supplies:

```powershell
$env:JWT_SECRET="..."
```

The JWT secret is therefore kept outside the Git-tracked source code.

### Database Password Configuration

The PostgreSQL password remains externalized through:

```powershell
$env:DB_PASSWORD
```

---

# Security Configuration

The backend security configuration is located at:

```text
src/main/java/com/tracen/dyp/config/SecurityConfig.java
```

The security configuration:

- Disables CSRF for the stateless REST API
- Uses stateless sessions
- Permits user registration
- Permits user login
- Requires authentication for protected endpoints
- Registers the JWT authentication filter

Relevant public endpoints:

```text
POST /api/users
POST /api/users/login
```

Protected endpoints require:

```http
Authorization: Bearer <JWT>
```

---

# Current User Authorization

Introduced / verified:

```text
CurrentUserService
```

The service retrieves the authenticated user ID from Spring Security's `SecurityContext`.

The protected profile workflow verifies that the authenticated user matches the requested `userId`.

Therefore:

```text
Authenticated User 10
        ↓
Request /api/users/10/profile
        ↓
Allowed ✅
```

while:

```text
Authenticated User 10
        ↓
Request /api/users/7/profile
        ↓
Access Denied ❌
```

This prevents one authenticated user from accessing another user's profile data.

---

# Phase 4 APIs

## Create Profile

```http
POST /api/users/{userId}/profile
Authorization: Bearer <JWT>
```

## Get Profile

```http
GET /api/users/{userId}/profile
Authorization: Bearer <JWT>
```

## Update Profile

```http
PUT /api/users/{userId}/profile
Authorization: Bearer <JWT>
```

## Profile Completion

```http
GET /api/users/{userId}/profile/completion
Authorization: Bearer <JWT>
```

---

# Phase 4 Profile Completion Logic

The completion workflow evaluates the ten required profile fields:

```text
1. Education
2. Degree
3. Graduation Year
4. Current Skills
5. Work Experience
6. Preferred Industries
7. Dream Roles
8. Weekly Learning Hours
9. Career Priorities
10. Preferred Work Location
```

Each completed field contributes to the completion percentage.

Formula:

```text
completionPercentage =
    (completedFields × 100) / totalFields
```

The workflow also returns whether the profile is fully completed.

---

# Phase 4 Verification

## 1. User Registration

Created a test user successfully:

```text
Email:
testuser2026@dyp.com

User ID:
10
```

Registration returned the created user.

```text
id   name       email
10   Test User  testuser2026@dyp.com
```

## 2. User Login

Successfully authenticated the test user.

The login response returned:

```text
id
name
email
token
```

The JWT was extracted and supplied to protected requests through:

```http
Authorization: Bearer <token>
```

## 3. Protected Profile Retrieval

Before profile creation, the API correctly reported:

```text
Profile not found
```

for:

```http
GET /api/users/10/profile
```

## 4. Profile Completion

After the profile was created, the completion endpoint returned:

```text
userId                10
profileExists         True
completionPercentage  100
completed             True
```

This confirms the completion calculation is working.

## 5. Profile Update

Updated the profile successfully using:

```http
PUT /api/users/10/profile
```

Verified profile data included:

```text
education:
B.Tech Artificial Intelligence and Data Science

degree:
B.Tech AI & DS

graduationYear:
2029

currentSkills:
Java, Python, C, SQL, Spring Boot, PostgreSQL

workExperience:
Student Developer

preferredIndustries:
Software, AI, FinTech

dreamRoles:
Software Engineer, AI Engineer

weeklyLearningHours:
25

careerPriorities:
SDE preparation, AI engineering, internships

preferredWorkLocation:
Remote
```

## 6. Profile Retrieval After Update

Verified the updated profile using:

```http
GET /api/users/10/profile
```

The persisted values were returned successfully.

## 7. Completion After Update

Verified:

```http
GET /api/users/10/profile/completion
```

Result:

```text
userId profileExists completionPercentage completed
------ ------------- -------------------- ---------
10     True          100                  True
```

## 8. Cross-User Access Protection

Authenticated as user `10` and attempted to access:

```http
GET /api/users/7/profile
```

The request was rejected with:

```text
403 Forbidden
```

This verifies the user-specific authorization boundary.

---

# Phase 4 Completion Matrix

```text
Student Profile Entity              ✅

User ↔ Profile Relationship         ✅

Profile Repository                  ✅

Create Profile DTO                  ✅

Profile Response DTO                ✅

Profile Service                     ✅

Profile Controller                  ✅

Create Profile API                  ✅

Get Profile API                     ✅

Update Profile API                  ✅

PostgreSQL Persistence              ✅

Profile Completion Calculation     ✅

Profile Completion API              ✅

JWT Authentication                 ✅

Protected Profile APIs              ✅

Current User Verification           ✅

Cross-User Access Protection        ✅

API Verification                    ✅

Database Verification               ✅

End-to-End Backend Profile Flow     ✅
```

> **Phase 4 — Profile & Goal Discovery is COMPLETE on the backend.**

---

# Phase 4 Completion Gate

The required development gate was completed:

```text
Requirements
     ↓
Implementation
     ↓
Compile
     ↓
Run
     ↓
Authentication Test
     ↓
Profile API Test
     ↓
Update Test
     ↓
Completion Test
     ↓
Authorization Test
     ↓
Database Verification
     ↓
Documentation Update
     ↓
Commit
     ↓
Push
```

Phase 5 can now be started.

---

# Current Architecture

```text
                         React / TypeScript
                                │
                                ▼
                           REST APIs
                                │
                                ▼
                         Spring Boot
                                │
        ┌───────────────────────┼───────────────────────┐
        │                       │                       │
        ▼                       ▼                       ▼
 Authentication           Profile Engine          Future Engines
        │                       │                       │
        └───────────────────────┼───────────────────────┘
                                │
                                ▼
                           PostgreSQL
```

## Backend Security Flow

```text
Client
  ↓
REST API
  ↓
Spring Security
  ↓
JWT Authentication Filter
  ↓
SecurityContext
  ↓
CurrentUserService
  ↓
Controller
  ↓
Service
  ↓
Repository
  ↓
PostgreSQL
```

---

# DYP Core Workflow

```text
User Registration
        ↓
Profile & Goal Discovery
        ↓
Assessment Planning
        ↓
Assessment
        ↓
Evidence Collection
        ↓
Scoring
        ↓
Talent Profile
        ↓
Career Intelligence
        ↓
Industry Readiness
        ↓
Gap Analysis
        ↓
Timeline Prediction
        ↓
Personalized Roadmap
        ↓
AI Recommendations
        ↓
DYP Report
        ↓
Student Dashboard
        ↓
Continuous Progress
```

---

# Future Intelligence Engines

The long-term DYP platform includes:

```text
Assessment Planning
Assessment
Evidence
Scoring
Talent Profile
Career Intelligence
Industry Readiness
Gap Analysis
Timeline Prediction
Personalized Roadmap
AI Recommendations
DYP Report
Dashboard
Continuous Progress
```

---

# Overall Project Progress by Day 10

```text
Project Foundation
        ↓
Backend Foundation
        ↓
User Registration
        ↓
Authentication
        ↓
Profile Foundation
        ↓
Profile APIs
        ↓
Profile Persistence
        ↓
Profile Completion
        ↓
JWT Authorization
        ↓
Phase 4 Complete
```

The system has progressed from a blank backend environment to a working Spring Boot + PostgreSQL foundation with:

- User registration
- Password hashing
- Login
- JWT authentication
- Protected endpoints
- Current-user authorization
- Student profile creation
- Student profile retrieval
- Student profile update
- Profile completion calculation
- PostgreSQL persistence

---

# Current Milestone

## Student Profile Engine

```text
Backend Foundation       ████████████████████ 100%

Authentication            ████████████████████ 100%

Profile Backend           ████████████████████ 100%

Phase 4                   ████████████████████ 100%

Assessment Planning      ░░░░░░░░░░░░░░░░░░░░   0%

Assessment Engine         ░░░░░░░░░░░░░░░░░░░░   0%

Scoring Engine            ░░░░░░░░░░░░░░░░░░░░   0%

Career Intelligence       ░░░░░░░░░░░░░░░░░░░░   0%

AI Engine                 ░░░░░░░░░░░░░░░░░░░░   0%

Dashboard                 ░░░░░░░░░░░░░░░░░░░░   0%
```

---

# Development Philosophy

DYP is being developed as a **backend-first, vertical-slice project**.

Each capability should become executable and testable before the next capability is started.

The development loop is:

```text
Understand Requirement
        ↓
Design
        ↓
Implement
        ↓
Compile
        ↓
Run
        ↓
Test API
        ↓
Verify Database
        ↓
Verify Requirement
        ↓
Document
        ↓
Commit
        ↓
Push
```

---

# Git Discipline

A completed phase must not remain as a WIP checkpoint.

The project rule is:

```text
Finish Phase
     ↓
Verify Everything
     ↓
Update Development Log
     ↓
git diff --check
     ↓
git status
     ↓
git add
     ↓
git commit
     ↓
git push
     ↓
Confirm clean working tree
     ↓
Start Next Phase
```

For Phase 4, the documentation update is currently visible as:

```text
modified: ../../docs/DYP_DEVELOPMENT_LOG.md
```

After saving this version, the documentation must be committed together with any other Phase 4 completion changes.

---

# Phase 4 Final Status

```text
╔════════════════════════════════════════════════════╗
║                                                    ║
║       PHASE 4 — PROFILE & GOAL DISCOVERY          ║
║                                                    ║
║                    COMPLETE ✅                     ║
║                                                    ║
╚════════════════════════════════════════════════════╝
```

Phase 4 backend requirements and verification are complete.

**Do not reopen Phase 4 unless a later integration test exposes a real defect.**

---

# Next Development Target

> **Phase 5 — Assessment Planning Engine**

The next stage should begin only after the Phase 4 completion commit has been created and pushed.

Expected progression:

```text
Phase 4 Complete
        ↓
Commit Documentation + Final Changes
        ↓
Push to GitHub
        ↓
Confirm Clean Working Tree
        ↓
Phase 5 — Assessment Planning
```

---

# End of Day 10

## Milestone Achieved

```text
TRACEN DYP

User
 ↓
Authentication
 ↓
JWT Security
 ↓
Profile & Goal Discovery
 ↓
Profile Completion
 ↓
Protected Student Context
```

**Phase 4 is officially complete on the backend.**

**Next: Phase 5 — Assessment Planning Engine.**
