package com.example.taskmanager.service;

import com.example.taskmanager.domain.Status;
import com.example.taskmanager.domain.Task;
import com.example.taskmanager.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(String title, String description) {
        Task task = new Task(title, description);
        return taskRepository.save(task);
    }

    public Task changeStatus(Long taskId, Status newStatus) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.changeStatus(newStatus);
        return taskRepository.save(task);
    }
}
