# DYP Talent Intelligence — Development Log

## Project

DYP Talent Intelligence

## Current Phase

Backend Foundation

## Date

2026-08-18

---

# Day 1 — Backend Environment + First User Flow

## 1. Environment Setup

### PostgreSQL

- PostgreSQL version: 18.4
- Host: localhost
- Port: 5432
- Username: postgres
- Database: dyp_db
- pgAdmin: configured and working
- psql: added to Windows PATH

### Important

The PostgreSQL password is NOT stored in Git.

Spring Boot reads it from:

```properties
spring.datasource.password=${DB_PASSWORD}