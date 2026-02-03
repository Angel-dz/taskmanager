package com.example.taskmanager.domain;

import com.example.taskmanager.domain.exception.InvalidStatusTransitionException;
import com.example.taskmanager.domain.exception.TaskNotFoundException;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;

/**
 * Domain entity that represents a task in the task management system.
 *
 * <p>This entity is part of the domain layer and models the core concept
 * of a task, including its title, description, and current status.</p>
 *
 * <p>The task enforces valid status transitions by delegating the
 * transition rules to the {@link Status} enum.</p>
 *
 * <p>Persistence-related concerns are handled via JPA annotations.</p>
 */
@Getter
@Entity
@Table(name = "tasks")
public class Task {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    /**
     * Protected no-args constructor required by JPA.
     */
    protected Task() {
        // Required by JPA
    }

    /**
     * Creates a new task with the given title and description.
     *
     * <p>The task is initialized with the {@link Status#TODO} status.</p>
     *
     * @param title short title of the task
     * @param description optional detailed description
     */
    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = Status.TODO;
    }

    /**
     * Changes the current status of the task.
     *
     * <p>The transition is validated according to the rules defined
     * in the {@link Status} enum.</p>
     *
     * @param newStatus the new status to transition to
     * @throws InvalidStatusTransitionException if the transition is not allowed
     */
    public void changeStatus(Status newStatus) {
        if (!this.status.canTransitionTo(newStatus)) {
            throw new InvalidStatusTransitionException(this.status, newStatus);
        }
        this.status = newStatus;
    }

    public Set<Status> getAvailableStatuses() {
        return this.getStatus().nextAllowedStatuses();
    }
}
