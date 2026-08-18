
# TRACEN DYP — Talent Intelligence Platform

> **DYP — Discover Your Potential**

DYP is the **Talent Intelligence Engine of TRACEN**, designed to understand a student's capabilities, career goals, industry readiness, skill gaps, and development trajectory.

The platform is being built to transform assessments, evidence, goals, and progress into a personalized **Talent Profile and Career Roadmap**.

---

## 🎯 Vision

DYP aims to go beyond traditional student assessment systems.

Instead of simply measuring marks or individual skills, DYP is designed to answer:

- **What can the student do?**
- **What are their strongest capabilities?**
- **Which career paths are compatible with their profile?**
- **How industry-ready are they?**
- **What skills are missing?**
- **How long could it take to close those gaps?**
- **What should they learn next?**
- **How should their progress be continuously tracked?**

The long-term output is a personalized **DYP Talent Profile, Industry Readiness Analysis, and Career Roadmap**.

---

# 🔄 Core Workflow

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
````


# 🧠 Main Intelligence Engines

| #  | Engine                   | Purpose                                                  |
| -- | ------------------------ | -------------------------------------------------------- |
| 1  | User Registration        | Create and manage student accounts                       |
| 2  | Profile & Goal Discovery | Understand background, interests, and goals              |
| 3  | Assessment Planning      | Determine appropriate assessments                        |
| 4  | Assessment               | Evaluate knowledge, skills, and capabilities             |
| 5  | Evidence Collection      | Collect projects, achievements, and other evidence       |
| 6  | Scoring                  | Convert assessments and evidence into structured signals |
| 7  | Talent Profile           | Build a unified student capability profile               |
| 8  | Career Intelligence      | Analyze career compatibility                             |
| 9  | Industry Readiness       | Estimate readiness for target roles                      |
| 10 | Gap Analysis             | Identify missing skills and competencies                 |
| 11 | Timeline Prediction      | Estimate development timelines                           |
| 12 | Personalized Roadmap     | Generate an actionable development path                  |
| 13 | AI Recommendations       | Recommend learning priorities and next actions           |
| 14 | DYP Report Generation    | Generate a comprehensive student report                  |
| 15 | Student Dashboard        | Present insights and progress                            |
| 16 | Continuous Progress      | Continuously update the talent profile                   |

---

# 🏗️ Architecture

DYP will initially be implemented as a **Modular Monolith**.

Each functional engine will be developed as an independent backend module while sharing the same Spring Boot application and PostgreSQL database.

```text
                    TRACEN DYP
                        │
                        ▼
                 React Frontend
                        │
                        ▼
              Spring Boot Backend
                        │
        ┌───────────────┼────────────────┐
        │               │                │
        ▼               ▼                ▼
   User Module    Assessment Module   Profile Module
        │               │                │
        └───────────────┼────────────────┘
                        │
                        ▼
                Intelligence Layer
                        │
        ┌───────────────┼────────────────┐
        │               │                │
        ▼               ▼                ▼
 Career Intelligence  Gap Analysis   Recommendations
        │               │                │
        └───────────────┼────────────────┘
                        ▼
                   PostgreSQL
```

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
* JavaScript / TypeScript

## Development Tools

* Git
* GitHub
* Postman
* VS Code

---

# 📂 Project Structure

```text
Tracen_DYP_Talent_Intelligence/
│
├── backend/
│   └── dyp-backend/
│       ├── src/
│       │   ├── main/
│       │   │   ├── java/
│       │   │   │   └── com/tracen/dyp/
│       │   │   │       ├── config/
│       │   │   │       ├── controller/
│       │   │   │       ├── dto/
│       │   │   │       ├── entity/
│       │   │   │       ├── repository/
│       │   │   │       ├── service/
│       │   │   │       └── exception/
│       │   │   │
│       │   │   └── resources/
│       │   │
│       │   └── test/
│       │
│       ├── pom.xml
│       ├── mvnw
│       └── mvnw.cmd
│
├── frontend/
│
├── database/
│
├── docs/
│   └── DYP_DEVELOPMENT_LOG.md
│
├── .gitignore
└── README.md
```

---

# 🚧 Development Status

**Status: Under Active Development**

## Phase 0 — Project Foundation

* [x] GitHub repository
* [x] Git configuration
* [x] Java `.gitignore`
* [x] Project structure
* [x] Backend initialization
* [x] PostgreSQL configuration
* [x] Database connection
* [x] First working API

## Phase 1 — Backend Foundation

* [x] Modular package structure
* [x] User entity
* [x] User repository
* [x] User service
* [x] User controller
* [x] Create User request DTO
* [x] User response DTO
* [x] User creation API
* [x] BCrypt password hashing
* [x] Password removed from API responses

### Next Development Tasks

* [ ] Request validation
* [ ] Duplicate email handling
* [ ] Global exception handling
* [ ] Login API
* [ ] Authentication
* [ ] JWT / protected endpoints
* [ ] Profile & Goal Discovery

---

# 🔌 Current Backend APIs

## Health Check

```http
GET /api/health
```

### Response

```text
DYP Backend is running
```

---

## Create User

```http
POST /api/users
```

### Request

```json
{
  "name": "Test User",
  "email": "test@example.com",
  "password": "password"
}
```

### Response

```json
{
  "id": 1,
  "name": "Test User",
  "email": "test@example.com"
}
```

> Passwords are hashed using BCrypt before being stored in PostgreSQL and are never returned through the API.

---

# 🗄️ Database

Current database:

```text
PostgreSQL 18.4
```

Database:

```text
dyp_db
```

Current core table:

```text
users
```

Current user model:

```text
users
├── id
├── name
├── email
└── password
```

---

# 🔐 Security Principles

DYP follows these principles from the backend foundation stage:

* Never store plaintext passwords.
* Hash passwords using BCrypt.
* Never expose passwords through API responses.
* Keep database credentials outside source control.
* Separate entities from API DTOs.
* Keep business logic inside service layers.
* Validate incoming API data before processing.

Database credentials are supplied through environment variables and are not committed to Git.

---

# 🧪 Development Workflow

Every feature follows the development cycle:

```text
Plan
  ↓
Implement
  ↓
Run
  ↓
Test
  ↓
Document
  ↓
Commit
  ↓
Push
```

Typical Git workflow:

```bash
git status
git add .
git commit -m "type: description"
git push origin main
```

---

# 📚 Documentation

Development progress is tracked in:

```text
docs/DYP_DEVELOPMENT_LOG.md
```

The development log records:

* Completed implementation
* APIs created
* Database changes
* Testing performed
* Architecture decisions
* Current milestone
* Next development tasks

---

# 🗺️ Long-Term Roadmap

```text
Foundation
    ↓
Authentication
    ↓
Profile & Goal Discovery
    ↓
Assessment System
    ↓
Evidence System
    ↓
Scoring Engine
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

# 🎯 Project Objective

DYP is being developed to answer four fundamental questions for every student:

> **Where am I?**

> **Where can I go?**

> **What is stopping me?**

> **What should I do next?**

The long-term objective is to continuously transform student data, assessments, evidence, goals, and progress into actionable career intelligence.

---

# 📈 Current Milestone

```text
Backend Foundation
        ↓
User Management
        ↓
Validation
        ↓
Exception Handling
        ↓
Authentication
        ↓
Profile & Goal Discovery
```

**Current:** Backend Foundation → User Management

**Next:** Validation → Exception Handling → Authentication

---

## 🚀 Status

**TRACEN DYP is actively under development.**

Building the foundation first. Intelligence comes next.

```
```
