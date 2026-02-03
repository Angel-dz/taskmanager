# Task Manager – Workflow-Oriented Backend
Backend project for a workflow-based task management system, inspired by tools like Jira.
The focus is on domain modeling, state transitions, and business rules, rather than on building a full-featured application.

This repository is part of a personal backend portfolio.

## Key Features
- Task lifecycle modeled with explicit workflow rules
- Domain-enforced state transitions (invalid states are impossible)
- Backend-driven workflow (single source of truth)
- Clear separation between domain, application, and API layers
- Domain-level tests validating business behavior

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
```
TODO <--> IN_PROGRESS <--> DONE
```
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

- **Domain** – business rules, workflow logic, and state validation
- **Application** – orchestration of use cases
- **API** – HTTP endpoints, input/output mapping, and error handling

The domain layer is independent of frameworks and infrastructure concerns.

## Testing
* Domain behavior is tested in isolation
* No Spring context is required for domain tests
* Workflow rules are covered by unit tests

## How to Run Locally

### Prerequisites
- Java 17+
- Gradle (or use the Gradle wrapper)

### Steps

1. Clone the repository:
    ```bash
    git clone https://github.com/your-username/task-manager.git
    cd task-manager
   ```
2. Run the application:
    ```bash
    ./gradlew bootRun
   ```
    (On Windows)
   ```bash
   gradlew.bat bootRun
   ```
4. The application will start on:
   http://localhost:8080
   
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