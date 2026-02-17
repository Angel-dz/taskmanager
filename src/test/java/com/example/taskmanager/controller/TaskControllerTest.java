package com.example.taskmanager.controller;

import com.example.taskmanager.domain.Status;
import com.example.taskmanager.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
@AutoConfigureMockMvc(addFilters = false)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TaskService taskService;

    @Test
    void shouldReturnAvailableStatuses() throws Exception {

        Set<Status> statuses = Set.of(Status.IN_PROGRESS);
        when(taskService.getAvailableStatuses(1L)).thenReturn(statuses);

        mockMvc.perform(get("/tasks/{id}/available-statuses", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(hasItem("IN_PROGRESS")));
    }
}
