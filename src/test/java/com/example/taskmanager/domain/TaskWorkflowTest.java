package com.example.taskmanager.domain;

import com.example.taskmanager.domain.exception.InvalidStatusTransitionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskWorkflowTest {

    @Test
    void should_move_from_todo_to_in_progress() {
        Task task = new Task("Test task", "Testing workflow");

        task.changeStatus(Status.IN_PROGRESS);

        assertEquals(Status.IN_PROGRESS, task.getStatus());
    }

    @Test
    void should_fail_when_moving_from_todo_to_done() {
        Task task = new Task("Test task", "Invalid transition");

        assertThrows(
                InvalidStatusTransitionException.class,
                () -> task.changeStatus(Status.DONE)
        );
    }

    @Test
    void should_allow_moving_back_from_in_progress_to_todo() {
        Task task = new Task("Rollback test", "Backwards transition");

        task.changeStatus(Status.IN_PROGRESS);
        task.changeStatus(Status.TODO);

        assertEquals(Status.TODO, task.getStatus());
    }

    @Test
    void should_allow_moving_from_done_back_to_in_progress() {
        Task task = new Task("Done test", "Reopen task");

        task.changeStatus(Status.IN_PROGRESS);
        task.changeStatus(Status.DONE);
        task.changeStatus(Status.IN_PROGRESS);

        assertEquals(Status.IN_PROGRESS, task.getStatus());
    }
}
