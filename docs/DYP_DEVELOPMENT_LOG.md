# DYP Talent Intelligence — Development Log

> **Project:** TRACEN DYP — Talent Intelligence Platform  
> **DYP:** Discover Your Potential  
> **Development Period:** Day 1 → Day 9  
> **Current Checkpoint:** Phase 4 — Profile & Goal Discovery  
> **Latest Commit:** `501e94e` — `wip: continue phase 4 profile workflow`

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
Database Connection    ✅
Backend Structure      ✅
User Persistence       ✅
Git/GitHub Setup       ✅
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

## Phase 4 Status

Profile backend was functional, but the phase was **not yet considered complete**.

Remaining work included profile update / completion workflow and full verification.

---

# Day 9 — Phase 4 Continuation + WIP Checkpoint

## Current Phase

> **Phase 4 — Profile & Goal Discovery**

## Current Work

Continued the remaining Profile workflow rather than moving prematurely to Assessment Planning.

The current implementation contains:

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

Current repository state:

```text
On branch main
Your branch is up to date with 'origin/main'.

nothing to commit, working tree clean
```

Latest commit:

```text
501e94e wip: continue phase 4 profile workflow
```

Recent project history:

```text
501e94e wip: continue phase 4 profile workflow
c28c915 docs: add DYP system architecture
b0ebe33 docs: add DYP system architecture
ebcd3e5 feat: add profile completion tracking
5e37287 Revise README for updated DYP platform focus and today progress
```

## Important Checkpoint

The latest commit deliberately remains a **WIP checkpoint**.

Phase 4 has **not** been marked finished yet.

The project rule is:

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

Therefore, the next development session should finish and verify the remaining Phase 4 requirements before starting Phase 5.

---

# Phase 4 — Current Completion Matrix

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
PostgreSQL Persistence              ✅

Update Profile API                  ⏳
Profile Completion Calculation      ⏳
Profile Frontend                    ⏳
End-to-End Profile Flow             ⏳
```

> **Phase 4 is therefore still in progress.**

---

# Overall Project Progress by Day 9

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
Phase 4 WIP
```

The system has progressed from a blank backend environment to a working Spring Boot + PostgreSQL foundation with user authentication and the initial Student Profile workflow.

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
       ┌───────────────────┼───────────────────┐
       │                   │                   │
       ▼                   ▼                   ▼
 Authentication      Profile Engine      Future Engines
       │                   │                   │
       └───────────────────┼───────────────────┘
                           │
                           ▼
                      PostgreSQL
```

Future engines include:

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
Commit
        ↓
Push
```

---

# Current Milestone

## Student Profile Engine

```text
Backend Foundation       ████████████████████ 100%
Authentication           ████████████████████ 100%*
Profile Backend          █████████████████░░░  85%
Assessment Planning      ░░░░░░░░░░░░░░░░░░░░   0%
Assessment Engine        ░░░░░░░░░░░░░░░░░░░░   0%
Scoring Engine           ░░░░░░░░░░░░░░░░░░░░   0%
Career Intelligence      ░░░░░░░░░░░░░░░░░░░░   0%
AI Engine                ░░░░░░░░░░░░░░░░░░░░   0%
Dashboard                ░░░░░░░░░░░░░░░░░░░░   0%
```

`*` Core registration/login functionality is implemented; JWT/protected authorization remains a later security requirement.

---

# Next Development Target

> **Finish Phase 4 — Profile & Goal Discovery.**

Do not begin Phase 5 until the remaining Phase 4 requirements are implemented and verified.

Next target:

```text
Update Profile API
        ↓
Profile Completion Calculation
        ↓
Profile API Verification
        ↓
Complete Phase 4
        ↓
Git Commit
        ↓
GitHub Push
        ↓
Phase 5 — Assessment Planning Engine
```

---

# End of Day 9

**Latest Git checkpoint:**

```text
501e94e
wip: continue phase 4 profile workflow
```

**Status:**

> Phase 4 Profile & Goal Discovery — **IN PROGRESS**
