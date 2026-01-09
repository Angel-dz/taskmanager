package com.example.taskmanager.service;

import com.example.taskmanager.domain.Status;
import com.example.taskmanager.domain.Task;
import com.example.taskmanager.repository.TaskRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;


public class TaskServiceTest {
    @Mock
    private TaskRepository taskRepository;

    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        taskService = new TaskService(taskRepository);
    }

    @Test
    void createTask_ShouldReturnSavedTask() {
        Task task = new Task("Test task", "Test description");

        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task created = taskService.createTask("Test task", "Test description");

        assertNotNull(created);
        assertEquals("Test task", created.getTitle());
        assertEquals(Status.TODO, created.getStatus());
        verify(taskRepository).save(any(Task.class));
    }

    @Test
    void changeStatus_ShouldUpdateStatus_WhenTaskExists() {
        Task task = new Task("Test", "Desc");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task updated = taskService.changeStatus(1L, Status.IN_PROGRESS);

        assertEquals(Status.IN_PROGRESS, updated.getStatus());
        verify(taskRepository).findById(1L);
        verify(taskRepository).save(task);
    }

    @Test
    void changeStatus_ShouldThrow_WhenTaskNotFound() {
        when(taskRepository.findById(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            taskService.changeStatus(1L, Status.DONE);
        });

        assertEquals("Task not found", exception.getMessage());
        verify(taskRepository).findById(1L);
        verify(taskRepository, never()).save(any(Task.class));
    }
}
