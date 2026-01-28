# Task Manager – Workflow-Oriented Backend
Backend project for a workflow-based task management system, inspired by tools like Jira.
The focus is on domain modeling, state transitions, and business rules, rather than on building a full-featured application.

This repository is part of a personal backend portfolio.

## Key Features
Task lifecycle with explicit workflow rules
* Validated state transitions (no invalid states possible)
* Backend-driven workflow (single source of truth)
* Clean separation between domain, application, and API layers
* Domain-level tests for workflow validation

## Domain Overview
### Task
Represents a unit of work that progresses through a predefined workflow.
Responsibilities:
* Hold its current status
* Change status only through valid transitions
* Protect its own consistency

### Status
Represents a state in the task lifecycle.
Responsibilities:
* Define allowed transitions
* Act as the source of truth for workflow rules

Current workflow:

TODO <--> IN_PROGRESS <--> DONE

## Domain Model Diagram
The diagram below represents the core domain model.
It intentionally excludes technical and infrastructure concerns.
See: docs/domain-diagram.png

## Workflow Design
From a user perspective:
* The UI presents only valid target states
* The user selects the desired next state

From a backend perspective:
* All transitions are validated by the domain
* The frontend is never trusted to enforce rules
* State changes are treated as business actions

## Architecture
The project follows a layered architecture with a rich domain model:
* Domain – business rules and workflow logic
* Application – use case orchestration
* API – HTTP endpoints and error handling

The domain is framework-agnostic and independent of infrastructure.

## Testing
* Domain behavior is tested in isolation
* No Spring context is required for domain tests
* Workflow rules are covered by unit tests

## Out of Scope (for now)
* Users and permissions
* Configurable workflows
* Status change history
* Frontend/UI

These features can be added later without changing the existing design.

## Tech Stack
* Java 17
* Spring Boot
* Spring Data JPA
* H2 (in-memory)
* JUnit 5

## Why this project?
This project emphasizes:
* Thoughtful domain modeling
* Explicit business rules
* Long-term maintainability

It is intentionally not a full Jira clone, but a solid backend foundation.

## License
This project is for educational and portfolio purposes.