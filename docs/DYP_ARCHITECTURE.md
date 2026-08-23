# TRACEN DYP — System Architecture

> **DYP — Discover Your Potential**  
> Talent Intelligence Platform of TRACEN

---

## 1. Architecture Overview

TRACEN DYP is designed as a **Modular Monolith**.

The platform combines student profile data, assessments, evidence, scoring, career intelligence, industry readiness, skill-gap analysis, timeline prediction, personalized roadmaps, recommendations, reports, and continuous progress into a single evolving **Talent Profile**.

### Core Technology Stack

| Layer | Technology |
|---|---|
| Frontend | React / TypeScript |
| Backend | Java / Spring Boot |
| Database | PostgreSQL |
| ORM | Spring Data JPA / Hibernate |
| API | REST / JSON |
| Build | Maven |
| Authentication | BCrypt + JWT (JWT planned) |
| Development | Git / GitHub / Postman / VS Code |

---

## 2. High-Level Architecture

```text
                         TRACEN DYP
                              |
                              v
                       React Frontend
                              |
                         REST / JSON
                              |
                              v
                    Spring Boot Backend
                              |
        +---------------------+----------------------+
        |                     |                      |
        v                     v                      v
 User & Profile         Assessment Layer       Intelligence Layer
        |                     |                      |
        v                     v                      v
 PostgreSQL            Evidence Collection     Intelligence Results
                              |
                              v
                         PostgreSQL
```

---

## 3. Complete DYP Intelligence Flow

```text
User Registration
        |
        v
Profile & Goal Discovery
        |
        v
Assessment Planning
        |
        v
Assessment Engine
        |
        v
Evidence Collection
        |
        v
Scoring Engine
        |
        v
Talent Profile
        |
        +------------------------+
        |                        |
        v                        v
Career Intelligence       Industry Readiness
        |                        |
        +------------+-----------+
                     |
                     v
                Gap Analysis
                     |
                     v
            Timeline Prediction
                     |
                     v
          Personalized Roadmap
                     |
                     v
           AI Recommendations
                     |
                     v
                DYP Report
                     |
                     v
             Student Dashboard
                     |
                     v
            Continuous Progress
                     |
                     v
          Updated Talent Profile
```

Each intelligence engine contributes to the student's overall Talent Profile.

---

## 4. Backend Architecture

The backend follows a modular-monolith structure.

```text
com.tracen.dyp
|
+-- config
|
+-- controller
|
+-- dto
|
+-- entity
|
+-- repository
|
+-- service
|
+-- exception
|
+-- assessment
|
+-- evidence
|
+-- scoring
|
+-- talent
|
+-- career
|
+-- readiness
|
+-- gap
|
+-- timeline
|
+-- roadmap
|
+-- recommendation
|
+-- report
|
+-- progress
```

The currently implemented common modules are:

- `config`
- `controller`
- `dto`
- `entity`
- `repository`
- `service`
- `exception`

Domain-specific modules will be introduced as their corresponding development phases are implemented.

---

## 5. Current Backend Structure

```text
backend/
└── dyp-backend/
    ├── pom.xml
    ├── mvnw
    ├── mvnw.cmd
    │
    └── src/
        └── main/
            ├── java/
            │   └── com/
            │       └── tracen/
            │           └── dyp/
            │               ├── DypBackendApplication.java
            │               │
            │               ├── config/
            │               │   └── PasswordConfig.java
            │               │
            │               ├── controller/
            │               │   ├── HealthController.java
            │               │   ├── UserController.java
            │               │   └── ProfileController.java
            │               │
            │               ├── dto/
            │               │   ├── CreateUserRequest.java
            │               │   ├── LoginRequest.java
            │               │   ├── LoginResponse.java
            │               │   ├── UserResponse.java
            │               │   ├── CreateProfileRequest.java
            │               │   ├── ProfileResponse.java
            │               │   └── ProfileCompletionResponse.java
            │               │
            │               ├── entity/
            │               │   ├── User.java
            │               │   └── Profile.java
            │               │
            │               ├── repository/
            │               │   ├── UserRepository.java
            │               │   └── ProfileRepository.java
            │               │
            │               ├── service/
            │               │   ├── UserService.java
            │               │   └── ProfileService.java
            │               │
            │               └── exception/
            │                   ├── GlobalExceptionHandler.java
            │                   └── InvalidCredentialsException.java
            │
            └── resources/
                └── application.properties
```

---

## 6. API Architecture

### 6.1 User APIs

#### `POST /api/users`

Creates a new DYP user account.

#### `POST /api/users/login`

Authenticates an existing user.

### 6.2 Profile APIs

#### `POST /api/users/{userId}/profile`

Creates the student's profile.

#### `GET /api/users/{userId}/profile`

Retrieves the student's profile.

#### `PUT /api/users/{userId}/profile`

Updates the student's profile.

#### `GET /api/users/{userId}/profile/completion`

Returns profile completion information.

### 6.3 Assessment Planning APIs

```text
POST /api/users/{userId}/assessment-plan
GET  /api/users/{userId}/assessment-plan
```

Creates and retrieves an assessment plan based on the student's profile, goals, and career interests.

### 6.4 Assessment APIs

```text
GET  /api/assessments/{assessmentId}
POST /api/assessments/{assessmentId}/attempts
POST /api/attempts/{attemptId}/responses
POST /api/attempts/{attemptId}/complete
```

These APIs support assessment execution and response collection.

### 6.5 Evidence APIs

```text
GET /api/users/{userId}/evidence
GET /api/users/{userId}/evidence/{evidenceId}
```

Provides access to evidence generated from assessments, projects, practical tasks, certifications, and other development activities.

### 6.6 Scoring APIs

```text
POST /api/users/{userId}/scores/calculate
GET  /api/users/{userId}/scores
```

Calculates and retrieves the student's intelligence scores.

### 6.7 Talent Profile APIs

```text
GET /api/users/{userId}/talent-profile
```

Returns the student's consolidated Talent Profile.

### 6.8 Career Intelligence APIs

```text
GET /api/users/{userId}/career-compatibility
GET /api/users/{userId}/career-compatibility/{careerId}
```

Returns career compatibility results.

### 6.9 Industry Readiness APIs

```text
GET /api/users/{userId}/readiness
```

Returns the student's industry readiness assessment.

### 6.10 Gap Analysis APIs

```text
GET /api/users/{userId}/gaps
```

Returns identified skill and competency gaps.

### 6.11 Timeline APIs

```text
GET /api/users/{userId}/timeline
```

Returns the predicted development timeline.

### 6.12 Roadmap APIs

```text
GET  /api/users/{userId}/roadmap
POST /api/users/{userId}/roadmap/generate
```

Retrieves or generates a personalized development roadmap.

### 6.13 Recommendation APIs

```text
GET /api/users/{userId}/recommendations
```

Returns personalized recommendations.

### 6.14 Report APIs

```text
GET /api/users/{userId}/report
```

Returns the student's DYP report.

### 6.15 Dashboard APIs

```text
GET /api/users/{userId}/dashboard
```

Provides the consolidated student dashboard data.

### 6.16 Continuous Progress APIs

```text
GET  /api/users/{userId}/progress
POST /api/users/{userId}/progress/events
```

Tracks continuous development activity.

---

## 7. Database Architecture

The database is organized into logical layers.

### Identity Layer

```text
users
profiles
```

Relationship:

```text
users 1 ───────── 1 profiles
```

### Career Layer

```text
careers
career_competencies
```

Relationship:

```text
career 1 ───────── N career_competencies
```

### Assessment Layer

```text
assessment_plans
assessments
questions
question_options
assessment_attempts
responses
```

Relationship:

```text
User
 |
 +── AssessmentPlan
          |
          +── Assessments
                  |
                  +── Questions
                          |
                          +── Options

User
 |
 +── AssessmentAttempt
          |
          +── Responses
```

### Evidence Layer

```text
evidence
```

Evidence may originate from:

- Assessment Response
- Practical Task
- Project
- Course
- Certification
- Interview
- Milestone

### Intelligence Layer

```text
scores
talent_profiles
career_compatibility
industry_readiness
skill_gaps
timeline_predictions
```

### Development Layer

```text
roadmaps
roadmap_items
recommendations
progress_events
```

---

## 8. Core Entity Relationships

```text
User
 |
 +── Profile
 |
 +── AssessmentPlan
 |       |
 |       +── Assessment
 |
 +── AssessmentAttempt
 |       |
 |       +── Response
 |
 +── Evidence
 |
 +── Score
 |
 +── TalentProfile
 |
 +── CareerCompatibility
 |
 +── IndustryReadiness
 |
 +── SkillGap
 |
 +── TimelinePrediction
 |
 +── Roadmap
 |       |
 |       +── RoadmapItem
 |
 +── Recommendation
 |
 +── ProgressEvent
```

---

## 9. Profile Architecture

The student's profile is the foundation for downstream intelligence.

```text
User
 |
 v
Profile
 |
 +── Education
 +── Degree
 +── Graduation Year
 +── Current Skills
 +── Work Experience
 +── Preferred Industries
 +── Dream Roles
 +── Weekly Learning Hours
 +── Career Priorities
 +── Preferred Work Location
 |
 v
Assessment Planning
```

The profile should not directly contain calculated intelligence scores.

Profile data represents student-provided context.

---

## 10. Assessment Architecture

Assessment planning is personalized.

```text
Profile
   +
Career Goals
   +
Career Interests
   |
   v
Assessment Planner
   |
   v
Assessment Plan
   |
   +── Cognitive Assessment
   +── Technical Assessment
   +── Problem Solving
   +── Domain Simulation
   +── Communication
   +── Learning Ability
   +── Interest Mapping
   +── Professional Behaviour
```

The Assessment Engine then executes the selected assessment components.

---

## 11. Evidence Architecture

DYP stores evidence rather than relying only on final scores.

```text
Assessment
     |
     v
Attempt
     |
     v
Response
     |
     v
Evidence
     |
     v
Scoring
```

Other evidence sources may include:

- Projects
- Practical Tasks
- Courses
- Certifications
- Interviews
- Milestones

Evidence provides the foundation for trustworthy intelligence.

---

## 12. Scoring Architecture

DYP maintains independent scoring dimensions.

- Cognitive Score
- Technical Foundation Score
- Execution Score
- Learning Potential Score
- Professional Skills Score

The consolidated DYP score is derived from these dimensions.

```text
DYP Score
    |
    +── Cognitive
    +── Technical Foundation
    +── Execution
    +── Learning Potential
    +── Professional Skills
```

Scores should be derived from evidence and assessment results rather than being manually stored as profile attributes.

---

## 13. Talent Profile Architecture

The Talent Profile is the central intelligence representation of the student.

```text
Evidence
   |
   v
Scores
   |
   v
Talent Profile
   |
   +── Strengths
   +── Weaknesses
   +── Cognitive Profile
   +── Technical Profile
   +── Professional Profile
   +── Learning Profile
   +── Execution Profile
```

The Talent Profile is continuously updated as new evidence becomes available.

---

## 14. Career Intelligence Architecture

```text
Talent Profile
      +
Career Competency Matrix
      |
      v
Career Compatibility
      |
      +── Strong Fit
      +── Potential Fit
      +── Skill Development Required
```

Career compatibility should be based on the student's measured competencies and the requirements of the selected career.

---

## 15. Industry Readiness Architecture

```text
Talent Profile
      +
Industry Requirements
      |
      v
Industry Readiness
      |
      +── Current Readiness
      +── Missing Competencies
      +── Development Priorities
```

The goal is to determine how prepared the student is for real-world industry expectations.

---

## 16. Gap Analysis Architecture

```text
Current Competency
        +
Target Career Competency
        |
        v
     Gap Analysis
        |
        +── Skill Gap
        +── Competency Gap
        +── Experience Gap
        +── Readiness Gap
```

The gap analysis feeds the roadmap and recommendations.

---

## 17. Timeline Prediction Architecture

```text
Current State
      +
Skill Gaps
      +
Learning Capacity
      +
Target Career
      |
      v
Timeline Prediction
      |
      +── Estimated Development Duration
      +── Milestones
      +── Target Readiness
```

The timeline is expected to change as the student's progress changes.

---

## 18. Personalized Roadmap Architecture

```text
Gap Analysis
      +
Timeline Prediction
      +
Learning Capacity
      |
      v
Personalized Roadmap
      |
      +── Learning Tasks
      +── Projects
      +── Practice
      +── Certifications
      +── Career Preparation
      +── Milestones
```

The roadmap is designed around the student's actual gaps rather than a generic learning path.

---

## 19. Recommendation Architecture

```text
Talent Profile
      +
Career Compatibility
      +
Industry Readiness
      +
Skill Gaps
      +
Roadmap
      |
      v
Recommendations
      |
      +── Skills to Learn
      +── Projects to Build
      +── Courses
      +── Certifications
      +── Practice
      +── Career Actions
```

---

## 20. DYP Report Architecture

```text
Talent Profile
      +
Career Intelligence
      +
Industry Readiness
      +
Gap Analysis
      +
Timeline
      +
Roadmap
      +
Recommendations
      |
      v
DYP Report
```

The report provides a consolidated representation of the student's current position and development direction.

---

## 21. Continuous Progress Architecture

DYP is designed as a continuous intelligence system.

```text
Course Completed
Assessment Completed
Project Completed
Certification Earned
Interview Completed
Milestone Achieved
        |
        v
Progress Event
        |
        v
Evidence
        |
        v
Scores Updated
        |
        v
Talent Profile Updated
        |
        v
Career Compatibility Updated
        |
        v
Industry Readiness Updated
        |
        v
Gap Analysis Updated
        |
        v
Timeline Updated
        |
        v
Recommendations Updated
```

This prevents DYP from becoming a one-time assessment platform.

---

## 22. Authentication Architecture

### Current Password Flow

```text
Password
   |
   v
BCrypt Hash
   |
   v
PostgreSQL
```

### Planned Authentication Flow

```text
Login Request
      |
      v
Credential Validation
      |
      v
JWT Generation
      |
      v
Client
      |
      v
Authorization: Bearer <token>
      |
      v
JWT Validation
      |
      v
Protected API
```

Passwords must never be returned in API responses.

---

## 23. Frontend Architecture

The frontend will follow a modular React structure.

```text
frontend/
|
└── src/
    |
    ├── components/
    |
    ├── pages/
    |
    ├── layouts/
    |
    ├── services/
    |
    ├── hooks/
    |
    ├── context/
    |
    ├── types/
    |
    ├── utils/
    |
    └── routes/
```

### Planned Major Screens

- Landing
- Registration
- Login
- Profile Setup
- Assessment Planner
- Assessment
- Assessment Results
- Talent Profile
- Career Intelligence
- Industry Readiness
- Gap Analysis
- Timeline
- Roadmap
- Recommendations
- DYP Report
- Dashboard
- Progress

---

## 24. Frontend → Backend Communication

```text
React Component
      |
      v
Frontend Service
      |
      v
REST API
      |
      v
Controller
      |
      v
Service
      |
      v
Repository
      |
      v
PostgreSQL
```

Responses return through the same chain:

```text
PostgreSQL
      |
      v
Repository
      |
      v
Service
      |
      v
DTO
      |
      v
REST Response
      |
      v
React Frontend
```

---

## 25. Security Principles

DYP will follow these principles:

- Passwords are hashed using BCrypt.
- Passwords are never returned in API responses.
- Authentication will use token-based authentication.
- Protected APIs will require authentication.
- User-specific resources must be authorized.
- Validation is performed at the API boundary.
- Business logic remains inside services.
- Database access remains inside repositories.
- DTOs are used for API request and response boundaries.

---

## 26. Development Principles

DYP follows these development principles.

### Modular

Each intelligence engine has a clear responsibility.

### Evidence-Driven

Intelligence should be derived from measurable evidence.

### Incremental

Each engine is implemented and tested independently.

### API-First

Backend capabilities are exposed through REST APIs before frontend integration.

### Maintainable

Controllers, services, repositories, entities, and DTOs have separate responsibilities.

### Extensible

New assessment types, careers, competencies, and recommendation strategies can be added without redesigning the entire system.

---

## 27. Current Implementation Status

### Foundation

- ✅ Spring Boot backend
- ✅ Maven
- ✅ PostgreSQL
- ✅ JPA / Hibernate
- ✅ REST API

### User Management

- ✅ User registration
- ✅ Input validation
- ✅ Duplicate email handling
- ✅ BCrypt password hashing
- ✅ Login endpoint
- ⏳ JWT authentication
- ⏳ Protected APIs

### Profile Discovery

- ✅ Profile entity
- ✅ Profile creation
- ✅ Profile retrieval
- ✅ Profile update
- ✅ Profile completion tracking
- ⏳ Frontend profile UI

### Assessment

- ⏳ Assessment planning
- ⏳ Assessment engine
- ⏳ Question system
- ⏳ Adaptive assessment

### Evidence

- ⏳ Evidence collection
- ⏳ Evidence storage

### Intelligence

- ⏳ Scoring engine
- ⏳ Talent Profile
- ⏳ Career Intelligence
- ⏳ Industry Readiness
- ⏳ Gap Analysis
- ⏳ Timeline Prediction

### Development

- ⏳ Personalized Roadmap
- ⏳ Recommendations
- ⏳ DYP Report
- ⏳ Dashboard
- ⏳ Continuous Progress

---

## 28. Target End-to-End System

```text
REGISTER
   |
   v
LOGIN
   |
   v
PROFILE & GOALS
   |
   v
ASSESSMENT PLANNING
   |
   v
ASSESSMENTS
   |
   v
EVIDENCE
   |
   v
SCORING
   |
   v
TALENT PROFILE
   |
   +------------------+
   |                  |
   v                  v
CAREER FIT       INDUSTRY READINESS
   |                  |
   +--------+---------+
            |
            v
       GAP ANALYSIS
            |
            v
      TIMELINE PREDICTION
            |
            v
    PERSONALIZED ROADMAP
            |
            v
     RECOMMENDATIONS
            |
            v
        DYP REPORT
            |
            v
       DASHBOARD
            |
            v
   CONTINUOUS PROGRESS
            |
            v
    UPDATED TALENT PROFILE
```

---

## 29. Architecture Goal

The objective of the architecture is to transform raw student information and continuous evidence into an evolving intelligence system.

```text
Student Data
     +
Assessment Data
     +
Evidence
     +
Career Requirements
     +
Industry Requirements
     |
     v
DYP Intelligence Engine
     |
     v
Talent Profile
     |
     v
Career Direction
     +
Readiness
     +
Skill Gaps
     +
Timeline
     +
Personalized Roadmap
     |
     v
Continuous Student Development
```

DYP is not designed as a static profile or assessment application.

It is designed as a continuously evolving Talent Intelligence Platform that converts student evidence into measurable career intelligence and actionable development decisions.
