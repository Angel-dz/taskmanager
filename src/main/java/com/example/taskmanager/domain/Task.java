package com.example.taskmanager.domain;

import jakarta.persistence.*;
import lombok.Getter;

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

    protected Task() {

    }

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = Status.TODO;
    }

    public void changeStatus(Status newStatus) {
        this.status = newStatus;
    }

}
