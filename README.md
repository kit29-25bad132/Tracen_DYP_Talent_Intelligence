# TRACEN DYP — Talent Intelligence Platform

> **DYP — Discover Your Potential**

DYP is the **Talent Intelligence Engine of TRACEN**, designed to understand an individual's capabilities, career goals, industry readiness, skill gaps, and development trajectory.

The platform transforms **student context, assessments, evidence, goals, and progress** into a continuously evolving **Talent Profile and Career Roadmap**.

---

## 🎯 Vision

DYP is designed to move beyond traditional platforms that focus only on courses, assessments, resumes, or job applications.

The objective is to create an evolving intelligence layer around every student:

```text
Student Registration
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

The long-term objective is to answer:

> **Where is this student now, where can they realistically reach, and what should they do next?**

---

# 🧠 DYP End-to-End Workflow

The DYP workflow is designed as a sequence of modular intelligence engines. Each engine contributes information to a continuously evolving Talent Profile.

```text
1. User Registration
        ↓
2. Profile & Goal Discovery
        ↓
3. Assessment Planning Engine
        ↓
4. Assessment Engine
        ↓
5. Evidence Collection Engine
        ↓
6. Scoring Engine
        ↓
7. Talent Profile Engine
        ↓
8. Career Intelligence Engine
        ↓
9. Industry Readiness Engine
        ↓
10. Gap Analysis Engine
        ↓
11. Timeline Prediction Engine
        ↓
12. Personalized Roadmap Engine
        ↓
13. AI Recommendation Engine
        ↓
14. DYP Report Generator
        ↓
15. Student Dashboard
        ↓
16. Continuous Progress Engine
```

The functional specification defines DYP as a modular system where individual engines operate independently while contributing to a single dynamic Talent Profile.

---

# 🚀 Development Status

DYP is being developed incrementally using a **backend-first, vertical-slice development approach**.

The current development checkpoint is:

```text
Current Phase: Phase 4 — Profile & Goal Discovery Engine

Current Git branch:
main

Current checkpoint:
501e94e — wip: continue phase 4 profile workflow
```

The Phase 4 implementation is currently being completed before development moves to Phase 5.

---

# 🗺️ Complete Development Roadmap

## Phase 0 — Project Foundation

* [x] Define project scope
* [x] Create GitHub repository
* [x] Create README
* [x] Create project structure
* [x] Initialize Git
* [x] Create backend and frontend directories
* [x] Define development workflow
* [x] Create `.gitignore`
* [x] Environment configuration

---

## Phase 1 — System Architecture

* [x] Modular-monolith architecture
* [x] Define DYP modules and engines
* [x] Define frontend architecture
* [x] Define backend architecture
* [x] Define API architecture
* [x] Define database architecture
* [x] Define entity relationships
* [x] Define complete user flow

---

## Phase 2 — Backend Foundation

* [x] Spring Boot application
* [x] Maven configuration
* [x] PostgreSQL integration
* [x] Spring Data JPA
* [x] Common package structure
* [x] API response structure
* [x] Exception handling
* [x] Request validation
* [x] Working REST API
* [x] API testing

---

## Phase 3 — User Registration & Authentication

* [x] User entity
* [x] User repository
* [x] Registration API
* [x] Login API
* [x] Password handling
* [x] Password hashing
* [x] Password verification
* [x] Invalid credentials handling
* [ ] Complete authentication/session-token flow
* [ ] Frontend registration
* [ ] Frontend login
* [ ] Protected routes
* [ ] Complete end-to-end authentication flow

---

## Phase 4 — Profile & Goal Discovery Engine

**Current development phase**

The Profile & Goal Discovery stage collects the contextual information required to personalize the later DYP engines. The functional specification identifies education, degree, graduation year, current skills, work experience, preferred industries, dream roles, weekly learning hours, career priorities, and preferred work location as the required context.

### Backend

* [x] Profile entity
* [x] User ↔ Profile relationship
* [x] Profile repository
* [x] Profile creation DTO
* [x] Profile response DTO
* [x] Profile service
* [x] Profile controller
* [x] Education information
* [x] Degree
* [x] Graduation year
* [x] Current skills
* [x] Work experience
* [x] Preferred industries
* [x] Dream roles
* [x] Weekly learning hours
* [x] Career priorities
* [x] Preferred work location
* [x] Profile creation API
* [x] Profile retrieval API
* [x] PostgreSQL persistence
* [ ] Profile update API
* [ ] Profile completion calculation
* [ ] Profile completion validation
* [ ] Complete authenticated profile ownership flow

### Frontend

* [ ] Profile setup UI
* [ ] Profile form
* [ ] Profile editing
* [ ] Profile completion indicator
* [ ] Profile validation
* [ ] End-to-end profile completion flow

### Phase 4 Exit Criteria

```text
Authenticated User
        ↓
Create / Update Profile
        ↓
Profile Validation
        ↓
Profile Completion Calculation
        ↓
Student Context Profile
        ↓
Ready for Assessment Planning
```

---

# Phase 5 — Assessment Planning Engine

* [ ] Career selection
* [ ] Career competency configuration
* [ ] Assessment categories
* [ ] Rule-based assessment planner
* [ ] Personalized assessment sequence
* [ ] Assessment plan API
* [ ] Assessment plan UI

The purpose of this engine is to determine which assessments are required based on the user's goals and career interests rather than giving every user the same assessment.

---

# Phase 6 — Assessment Engine

* [ ] Assessment entity
* [ ] Question entity
* [ ] Options
* [ ] Assessment categories
* [ ] Cognitive assessment
* [ ] Technical assessment
* [ ] Problem-solving assessment
* [ ] Domain simulation
* [ ] Communication assessment
* [ ] Learning ability assessment
* [ ] Interest mapping
* [ ] Professional behaviour assessment
* [ ] Assessment UI
* [ ] Question navigation
* [ ] Timer
* [ ] Answer submission
* [ ] Assessment completion

The assessment stage is designed to collect evidence across cognitive, technical, problem-solving, domain, communication, learning ability, interest, and professional behaviour dimensions.

---

# Phase 7 — Evidence Collection Engine

* [ ] Assessment attempt entity
* [ ] Response entity
* [ ] Evidence entity
* [ ] Store every response
* [ ] Store correctness
* [ ] Store time taken
* [ ] Store assessment result evidence
* [ ] Central evidence repository
* [ ] Evidence APIs

DYP is designed to store measurable evidence rather than relying only on final scores.

---

# Phase 8 — Scoring Engine

* [ ] Cognitive Score
* [ ] Technical Foundation Score
* [ ] Execution Score
* [ ] Learning Potential Score
* [ ] Professional Skills Score
* [ ] DYP weighted score
* [ ] Score calculation service
* [ ] Score validation
* [ ] Score API
* [ ] Results UI

The target DYP score model is:

```text
DYP Score =
25% Cognitive Score
20% Technical Score
20% Execution Score
15% Learning Potential Score
20% Professional Skills Score
```

---

# Phase 9 — Talent Profile Engine

* [ ] Strength detection
* [ ] Weakness detection
* [ ] Cognitive profile
* [ ] Technical profile
* [ ] Professional skills profile
* [ ] Learning profile
* [ ] Execution profile
* [ ] Talent Profile API
* [ ] Talent Profile UI

The Talent Profile combines assessment results into a multidimensional capability profile rather than reducing the user to one score.

---

# Phase 10 — Career Intelligence Engine

* [ ] Career entity
* [ ] Career competency matrix
* [ ] User competency comparison
* [ ] Career compatibility calculation
* [ ] Career ranking
* [ ] Career recommendation
* [ ] Career compatibility API
* [ ] Career comparison UI

Career compatibility will compare the user's Talent Profile against predefined competency matrices for career roles.

---

# Phase 11 — Industry Readiness Engine

* [ ] Knowledge readiness
* [ ] Execution readiness
* [ ] Project readiness
* [ ] Professional readiness
* [ ] Interview readiness
* [ ] Industry readiness calculation
* [ ] Readiness API
* [ ] Readiness dashboard

---

# Phase 12 — Gap Analysis Engine

* [ ] Compare current competency against career requirements
* [ ] Missing skills
* [ ] Weak competencies
* [ ] Priority calculation
* [ ] Skill gap ranking
* [ ] Gap analysis API
* [ ] Gap visualization

---

# Phase 13 — Timeline Prediction Engine

* [ ] Current readiness input
* [ ] Learning velocity
* [ ] Weekly learning hours
* [ ] Role complexity
* [ ] Timeline calculation
* [ ] Estimated months
* [ ] Timeline API
* [ ] Timeline UI

---

# Phase 14 — Personalized Roadmap Engine

* [ ] Learning sequence
* [ ] Weekly goals
* [ ] Projects
* [ ] Certifications
* [ ] Practice plan
* [ ] Interview preparation
* [ ] Roadmap generation
* [ ] Roadmap API
* [ ] Roadmap UI
* [ ] Progress tracking

---

# Phase 15 — AI Recommendation Engine

* [ ] Recommendation model
* [ ] Recommendation inputs
* [ ] Courses
* [ ] Projects
* [ ] Coding challenges
* [ ] Certifications
* [ ] Mock interviews
* [ ] Practice activities
* [ ] AI integration
* [ ] Recommendation API
* [ ] Recommendation UI
* [ ] Rule-based fallback recommendations

---

# Phase 16 — DYP Report Generator

* [ ] Report data aggregation
* [ ] Overall DYP score
* [ ] Talent Profile
* [ ] Career compatibility
* [ ] Industry readiness
* [ ] Gap analysis
* [ ] Timeline prediction
* [ ] Personalized roadmap
* [ ] AI recommendations
* [ ] Report generation
* [ ] Downloadable DYP report

The final report is intended to consolidate the major outputs of the DYP intelligence pipeline.

---

# Phase 17 — Student Dashboard

* [ ] DYP level
* [ ] DYP score
* [ ] Talent Profile
* [ ] Career compatibility
* [ ] Industry readiness
* [ ] Active roadmap
* [ ] Assessment history
* [ ] Projects
* [ ] Progress trends
* [ ] Achievement badges
* [ ] Dashboard API
* [ ] Complete dashboard UI

---

# Phase 18 — Continuous Progress Engine

* [ ] Course completion event
* [ ] Assessment completion event
* [ ] Project completion event
* [ ] Certification event
* [ ] Interview event
* [ ] Milestone event
* [ ] Evidence update
* [ ] Score update
* [ ] Talent Profile update
* [ ] Career compatibility update
* [ ] Readiness update
* [ ] Timeline update
* [ ] Recommendation update

The goal is a continuously evolving DYP Talent Profile that changes as the user completes courses, assessments, projects, certifications, interviews, and milestones.

---

# Phase 19 — Complete Frontend UX

* [ ] Landing page
* [ ] Registration
* [ ] Login
* [ ] Profile setup
* [ ] Assessment planner
* [ ] Assessment interface
* [ ] Results
* [ ] Talent Profile
* [ ] Career Intelligence
* [ ] Industry Readiness
* [ ] Gap Analysis
* [ ] Timeline
* [ ] Roadmap
* [ ] Recommendations
* [ ] Report
* [ ] Dashboard
* [ ] Loading states
* [ ] Empty states
* [ ] Error states
* [ ] Mobile responsiveness

---

# Phase 20 — Testing

* [ ] Unit tests
* [ ] Service tests
* [ ] Repository tests
* [ ] API tests
* [ ] Authentication tests
* [ ] Assessment tests
* [ ] Scoring tests
* [ ] Career matching tests
* [ ] Gap analysis tests
* [ ] Roadmap tests
* [ ] Frontend testing
* [ ] End-to-end user flow
* [ ] Edge cases
* [ ] Invalid input testing

---

# Phase 21 — Security & Quality

* [ ] Input validation
* [ ] Password security
* [ ] Authentication security
* [ ] Authorization
* [ ] Environment variables
* [ ] API error handling
* [ ] Database constraints
* [ ] Logging
* [ ] Remove hardcoded secrets
* [ ] Code cleanup

---

# Phase 22 — Deployment

* [ ] Production database
* [ ] Backend deployment
* [ ] Frontend deployment
* [ ] Environment configuration
* [ ] CORS
* [ ] Production testing
* [ ] GitHub final release
* [ ] README documentation

---

# Phase 23 — Final Demo

The final DYP demonstration should verify the complete intelligence pipeline:

```text
Register User
      ↓
Complete Profile
      ↓
Select Career
      ↓
Receive Assessment Plan
      ↓
Complete Assessment
      ↓
Evidence Stored
      ↓
Scores Calculated
      ↓
Talent Profile Generated
      ↓
Career Compatibility Generated
      ↓
Industry Readiness Generated
      ↓
Skill Gaps Generated
      ↓
Timeline Generated
      ↓
Roadmap Generated
      ↓
Recommendations Generated
      ↓
Report Generated
      ↓
Dashboard Updated
      ↓
Complete New Activity
      ↓
Verify Continuous Profile Update
```

---

# 🏗️ Architecture

DYP is being developed as a **modular monolith**.

```text
                         ┌────────────────────────┐
                         │        Frontend        │
                         │    React / TypeScript  │
                         └────────────┬───────────┘
                                      │
                                      ▼
                         ┌────────────────────────┐
                         │       REST APIs        │
                         │      Spring Boot       │
                         └────────────┬───────────┘
                                      │
              ┌───────────────────────┼───────────────────────┐
              │                       │                       │
              ▼                       ▼                       ▼
       Authentication          Profile & Goals        Intelligence
              │                       │                  Engines
              │                       │                       │
              │                       │        ┌──────────────┼──────────────┐
              │                       │        │              │              │
              │                       │        ▼              ▼              ▼
              │                       │   Assessment      Scoring        Career
              │                       │   & Evidence      & Talent      Intelligence
              │                       │
              └───────────────────────┼───────────────────────┘
                                      │
                                      ▼
                             ┌─────────────────┐
                             │   PostgreSQL    │
                             └─────────────────┘
```

The architecture is intentionally designed so that future DYP intelligence engines can be added as modules without rewriting the core foundation.

---

# 🛠️ Technology Stack

## Backend

* Java
* Spring Boot
* Spring Data JPA
* Hibernate
* Maven

## Database

* PostgreSQL

## Frontend

* React
* TypeScript

## Development Tools

* Git
* GitHub
* Postman
* PowerShell
* VS Code

---

# 📁 Current Backend Structure

```text
backend/
└── dyp-backend/
    ├── src/
    │   └── main/
    │       ├── java/
    │       │   └── com/
    │       │       └── tracen/
    │       │           └── dyp/
    │       │               ├── config/
    │       │               │   └── PasswordConfig.java
    │       │               │
    │       │               ├── controller/
    │       │               │   ├── HealthController.java
    │       │               │   ├── UserController.java
    │       │               │   └── ProfileController.java
    │       │               │
    │       │               ├── dto/
    │       │               │   ├── CreateUserRequest.java
    │       │               │   ├── LoginRequest.java
    │       │               │   ├── LoginResponse.java
    │       │               │   ├── UserResponse.java
    │       │               │   ├── CreateProfileRequest.java
    │       │               │   └── ProfileResponse.java
    │       │               │
    │       │               ├── entity/
    │       │               │   ├── User.java
    │       │               │   └── Profile.java
    │       │               │
    │       │               ├── exception/
    │       │               │   ├── GlobalExceptionHandler.java
    │       │               │   └── InvalidCredentialsException.java
    │       │               │
    │       │               ├── repository/
    │       │               │   ├── UserRepository.java
    │       │               │   └── ProfileRepository.java
    │       │               │
    │       │               └── service/
    │       │                   ├── UserService.java
    │       │                   └── ProfileService.java
    │       │
    │       └── resources/
    │
    └── pom.xml
```

---

# 🔐 Authentication

Current authentication APIs:

### Register

```http
POST /api/users
```

Example:

```json
{
  "name": "Rishi",
  "email": "rishi@example.com",
  "password": "password"
}
```

### Login

```http
POST /api/users/login
```

Example:

```json
{
  "email": "rishi@example.com",
  "password": "password"
}
```

---

# 👤 Profile & Goal Discovery

The Profile module is the first contextual intelligence layer of DYP.

Current profile data model:

```text
Profile
├── Education
├── Degree
├── Graduation Year
├── Current Skills
├── Work Experience
├── Preferred Industries
├── Dream Roles
├── Weekly Learning Hours
├── Career Priorities
└── Preferred Work Location
```

Current API endpoints:

```http
POST /api/users/{userId}/profile
GET  /api/users/{userId}/profile
```

> These endpoints represent the current development checkpoint. The profile update and complete profile workflow are still being implemented.

---

# 🗄️ Current Database Model

```text
┌──────────────────┐
│      users       │
├──────────────────┤
│ id               │
│ name             │
│ email            │
│ password         │
└────────┬─────────┘
         │
         │ 1 : 1
         │
         ▼
┌──────────────────────────┐
│        profiles          │
├──────────────────────────┤
│ id                       │
│ user_id                  │
│ education                │
│ degree                   │
│ graduation_year          │
│ current_skills           │
│ work_experience          │
│ preferred_industries     │
│ dream_roles              │
│ weekly_learning_hours    │
│ career_priorities        │
│ preferred_work_location  │
└──────────────────────────┘
```

---

# 🧪 Development & Verification Strategy

DYP follows a vertical-slice development model:

```text
Feature
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
   ↓
Verification
   ↓
Git Commit
   ↓
GitHub
```

A phase is not considered complete merely because its classes exist.

The intended completion process is:

```text
Implementation
     ↓
Compilation
     ↓
Runtime verification
     ↓
API testing
     ↓
Requirement verification
     ↓
Git commit
     ↓
GitHub push
```

---

# 🧩 DYP Intelligence Architecture

```text
                         TRACEN DYP
                             │
                             ▼
                    Student Context
                             │
                             ▼
                    Profile & Goals
                             │
                             ▼
                  Assessment Planning
                             │
                             ▼
                       Assessment
                             │
                             ▼
                  Evidence Repository
                             │
                             ▼
                         Scoring
                             │
                             ▼
                     Talent Profile
                             │
              ┌──────────────┼──────────────┐
              ▼              ▼              ▼
          Strengths       Weaknesses     Capabilities
              │              │              │
              └──────────────┼──────────────┘
                             ▼
                   Career Intelligence
                             │
                             ▼
                   Industry Readiness
                             │
                             ▼
                      Gap Analysis
                             │
                             ▼
                  Timeline Prediction
                             │
                             ▼
                 Personalized Roadmap
                             │
                             ▼
                  AI Recommendations
                             │
                             ▼
                       DYP Report
                             │
                             ▼
                    Student Dashboard
                             │
                             ▼
                  Continuous Progress
                             │
                             └──────────────┐
                                            │
                                            ▼
                                   Updated Evidence
                                            │
                                            ▼
                                  Evolving Talent Profile
```

---

# 🔮 Long-Term Capabilities

The completed DYP platform is intended to provide:

* Adaptive assessment planning
* Multi-dimensional talent scoring
* Evidence-backed competency analysis
* Career compatibility analysis
* Industry readiness measurement
* Skill gap prioritization
* Learning velocity analysis
* Timeline prediction
* Personalized learning roadmaps
* AI-powered recommendations
* Continuous Talent Profile evolution
* Comprehensive DYP reporting
* Student progress monitoring

---

# 📌 Current Milestone

```text
Phase 0 — Project Foundation          COMPLETE
Phase 1 — System Architecture         COMPLETE
Phase 2 — Backend Foundation          COMPLETE
Phase 3 — Authentication              IN PROGRESS
Phase 4 — Profile & Goal Discovery    IN PROGRESS
Phase 5 — Assessment Planning         NOT STARTED
Phase 6 — Assessment Engine           NOT STARTED
Phase 7 — Evidence Collection         NOT STARTED
Phase 8 — Scoring Engine              NOT STARTED
Phase 9 — Talent Profile              NOT STARTED
Phase 10 — Career Intelligence        NOT STARTED
Phase 11 — Industry Readiness         NOT STARTED
Phase 12 — Gap Analysis               NOT STARTED
Phase 13 — Timeline Prediction        NOT STARTED
Phase 14 — Personalized Roadmap       NOT STARTED
Phase 15 — AI Recommendations         NOT STARTED
Phase 16 — DYP Report                 NOT STARTED
Phase 17 — Student Dashboard          NOT STARTED
Phase 18 — Continuous Progress        NOT STARTED
Phase 19 — Complete Frontend UX       NOT STARTED
Phase 20 — Testing                    NOT STARTED
Phase 21 — Security & Quality         NOT STARTED
Phase 22 — Deployment                 NOT STARTED
Phase 23 — Final Demo                 NOT STARTED
```

### Immediate objective

> **Finish Phase 4 — Profile & Goal Discovery Engine.**

The next implementation checkpoint is to complete the remaining profile workflow, verify it end-to-end, and only then move to **Phase 5 — Assessment Planning Engine**.

---

# 📈 Product Direction

DYP is ultimately intended to become a **living Talent Intelligence system** rather than a one-time assessment application.

Every meaningful student activity can contribute new evidence and update:

```text
Evidence
   ↓
Scores
   ↓
Talent Profile
   ↓
Career Compatibility
   ↓
Industry Readiness
   ↓
Timeline
   ↓
Recommendations
   ↓
Roadmap
```

This creates a continuously evolving professional identity that reflects the student's development over time.

---

# 👨‍💻 Project

**TRACEN DYP — Discover Your Potential**

A Talent Intelligence Platform designed to transform student context, evidence, assessments, goals, and progress into actionable career intelligence.

---

## ⚠️ Development Status

DYP is under active development.

The current implementation is focused on completing the **Profile & Goal Discovery Engine** before progressing to the **Assessment Planning Engine**.

Architecture, APIs, database models, and intelligence modules will continue to evolve throughout the 24-phase development roadmap.

---

**TRACEN DYP — Discover Your Potential**
