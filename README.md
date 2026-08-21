
# TRACEN DYP — Talent Intelligence Platform

> **DYP — Discover Your Potential**

DYP is the **Talent Intelligence Engine of TRACEN**, designed to understand a student's capabilities, career goals, industry readiness, skill gaps, and development trajectory.

The platform is being built to transform **student information, assessments, evidence, goals, and progress** into a personalized **Talent Profile and Career Roadmap**.

---

## 🎯 Vision

DYP aims to move beyond traditional student platforms that focus only on courses, assessments, resumes, or job applications.

The goal is to create an evolving intelligence layer around every student:

```text
Student Context
      ↓
Goals & Career Interests
      ↓
Assessment Planning
      ↓
Assessments
      ↓
Evidence Collection
      ↓
Scoring & Analysis
      ↓
Talent Profile
      ↓
Career Intelligence
      ↓
Industry Readiness
      ↓
Skill Gap Analysis
      ↓
Timeline Prediction
      ↓
Personalized Roadmap
      ↓
AI Recommendations
      ↓
Continuous Progress
````

The long-term objective is to answer:

> **Where is this student now, where can they realistically reach, and what should they do next?**

---

# 🧠 Core DYP Workflow

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

# 🚀 Current Development Status

DYP is being developed incrementally using a **backend-first, vertical-slice approach**.

## Phase 1 — Project Foundation

* [x] Spring Boot project setup
* [x] Maven configuration
* [x] PostgreSQL integration
* [x] Basic backend structure
* [x] Git/GitHub repository

## Phase 2 — Backend Foundation

* [x] Entity layer
* [x] Repository layer
* [x] Service layer
* [x] Controller layer
* [x] DTO-based API design
* [x] Request validation
* [x] Global exception handling

## Phase 3 — User Authentication

* [x] User registration
* [x] Duplicate email detection
* [x] Password hashing
* [x] Password verification
* [x] Login API
* [x] Invalid credentials handling
* [x] Authentication API testing

## Phase 4 — Profile & Goal Discovery

* [x] Student Profile entity
* [x] User ↔ Profile relationship
* [x] Profile repository
* [x] Profile creation DTO
* [x] Profile response DTO
* [x] Profile service
* [x] Profile controller
* [x] Create Profile API
* [x] Get Profile API
* [x] PostgreSQL persistence verification
* [ ] Update Profile API
* [ ] Profile completion calculation
* [ ] Profile frontend
* [ ] End-to-end profile completion flow

## Phase 5 — Assessment Planning Engine

* [ ] Assessment categories
* [ ] Assessment templates
* [ ] Goal-based assessment planning
* [ ] Adaptive assessment sequence
* [ ] Assessment recommendation API

## Phase 6 — Assessment Engine

* [ ] Question bank
* [ ] Assessment sessions
* [ ] Question delivery
* [ ] Answer submission
* [ ] Assessment completion
* [ ] Adaptive difficulty

## Phase 7 — Evidence Collection

* [ ] Evidence entity
* [ ] Project evidence
* [ ] Certification evidence
* [ ] Internship evidence
* [ ] Hackathon evidence
* [ ] GitHub/project evidence
* [ ] Evidence verification

## Phase 8 — Scoring Engine

* [ ] Skill scoring
* [ ] Evidence scoring
* [ ] Assessment scoring
* [ ] Confidence calculation
* [ ] Talent dimension scoring

## Phase 9 — Talent Profile Engine

* [ ] Talent dimensions
* [ ] Strength identification
* [ ] Weakness identification
* [ ] Talent profile generation
* [ ] Profile evolution

## Phase 10 — Career Intelligence

* [ ] Career role mapping
* [ ] Industry mapping
* [ ] Career fit analysis
* [ ] Role recommendations

## Phase 11 — Industry Readiness

* [ ] Industry skill requirements
* [ ] Readiness scoring
* [ ] Job-role readiness
* [ ] Industry benchmarks

## Phase 12 — Gap Analysis

* [ ] Skill gap detection
* [ ] Priority gaps
* [ ] Critical skill identification
* [ ] Gap severity scoring

## Phase 13 — Timeline Prediction

* [ ] Current capability estimation
* [ ] Target capability estimation
* [ ] Learning velocity
* [ ] Timeline prediction
* [ ] Readiness forecasting

## Phase 14 — Personalized Roadmap

* [ ] Learning roadmap
* [ ] Skill milestones
* [ ] Project recommendations
* [ ] Assessment milestones
* [ ] Career milestones

## Phase 15 — AI Recommendation Engine

* [ ] Personalized recommendations
* [ ] Next-best-action engine
* [ ] Learning recommendations
* [ ] Project recommendations
* [ ] Career recommendations

## Phase 16 — DYP Report

* [ ] Talent report generation
* [ ] Career readiness report
* [ ] Skill gap report
* [ ] Roadmap report

## Phase 17 — Student Dashboard

* [ ] Talent overview
* [ ] Career readiness
* [ ] Skill gaps
* [ ] Roadmap
* [ ] Progress tracking
* [ ] Recommendations

## Phase 18 — Continuous Progress Engine

* [ ] Progress tracking
* [ ] New evidence ingestion
* [ ] Score updates
* [ ] Talent profile evolution
* [ ] Roadmap adaptation

---

# 🏗️ Architecture

DYP is currently being developed as a **modular monolith**.

```text
                    ┌───────────────────────┐
                    │       Frontend        │
                    │   React / TypeScript  │
                    └───────────┬───────────┘
                                │
                                ▼
                    ┌───────────────────────┐
                    │      REST APIs        │
                    │     Spring Boot       │
                    └───────────┬───────────┘
                                │
          ┌─────────────────────┼─────────────────────┐
          │                     │                     │
          ▼                     ▼                     ▼
   Authentication        Profile Engine       Future Engines
          │                     │                     │
          ▼                     ▼                     ▼
       User DB             Profile DB       Assessment / AI /
                                             Career / Scoring
                                │
                                ▼
                       ┌─────────────────┐
                       │   PostgreSQL    │
                       └─────────────────┘
```

The architecture is designed so that additional intelligence engines can be introduced without rewriting the core foundation.

---

# 🛠️ Tech Stack

### Backend

* Java
* Spring Boot
* Spring Data JPA
* Hibernate
* Maven

### Database

* PostgreSQL

### Frontend

* React
* JavaScript / TypeScript

### Development Tools

* Git
* GitHub
* Postman / PowerShell
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

# 🔐 Authentication APIs

## Register User

```http
POST /api/users
```

Example request:

```json
{
  "name": "Rishi",
  "email": "rishi@example.com",
  "password": "password"
}
```

---

## Login

```http
POST /api/users/login
```

Example request:

```json
{
  "email": "rishi@example.com",
  "password": "password"
}
```

---

# 👤 Profile APIs

## Create Student Profile

```http
POST /api/users/{userId}/profile
```

Example:

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

## Get Student Profile

```http
GET /api/users/{userId}/profile
```

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

# 🧪 Verified Backend Flow

The current implementation has been tested against the local PostgreSQL database.

```text
Registration
    ↓
User created
    ↓
Login
    ↓
Password verified
    ↓
Profile creation
    ↓
Profile stored in PostgreSQL
    ↓
Profile retrieval
```

The Profile API has been successfully verified with:

```text
User ID: 7
Profile ID: 1
```

---

# 📊 Development Approach

DYP is being built using a **vertical-slice development approach**.

Each feature follows:

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
Git Commit
```

The objective is to keep every completed feature executable and testable before moving to the next intelligence layer.

---

# 🗺️ Long-Term Intelligence Architecture

```text
                    TRACEN DYP
                        │
        ┌───────────────┴────────────────┐
        │                                │
   Student Context                 Evidence Layer
        │                                │
        ▼                                ▼
 Profile & Goals                  Projects / Skills
        │                         Certifications
        ▼                         Internships
 Assessment Planning              Hackathons / GitHub
        │                                │
        └───────────────┬────────────────┘
                        ▼
                 Intelligence Layer
                        │
        ┌───────────────┼────────────────┐
        ▼               ▼                ▼
     Scoring         Talent          Career
     Engine          Profile       Intelligence
        │               │                │
        └───────────────┼────────────────┘
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
```

---

# 🔮 Future Capabilities

The long-term DYP platform is intended to support:

* Adaptive assessments
* Multi-dimensional talent scoring
* Evidence-backed skill verification
* Career-role matching
* Industry readiness prediction
* Skill gap prioritization
* Learning velocity analysis
* Career timeline prediction
* Personalized learning roadmaps
* AI-powered next-best-action recommendations
* Continuous talent profile evolution

---

# 📌 Current Milestone

### Student Profile Engine

```text
Backend Foundation    ████████████████████  100%
Authentication        ████████████████████  100%
Profile Backend       █████████████████░░░   85%
Assessment Engine     ░░░░░░░░░░░░░░░░░░░░    0%
Scoring Engine        ░░░░░░░░░░░░░░░░░░░░    0%
Career Intelligence   ░░░░░░░░░░░░░░░░░░░░    0%
AI Engine             ░░░░░░░░░░░░░░░░░░░░    0%
Dashboard             ░░░░░░░░░░░░░░░░░░░░    0%
```

> **Current focus:** Complete the remaining Profile & Goal Discovery functionality, then begin the Assessment Planning Engine.

---

# 👨‍💻 Project

**TRACEN DYP — Discover Your Potential**

A student-focused Talent Intelligence Platform designed to transform student data, evidence, assessments, and goals into actionable career intelligence.

---

## ⚠️ Development Status

DYP is currently under active development.

The architecture, APIs, database schema, and intelligence modules will continue to evolve as implementation progresses.

```


