package com.denver7074.taskmanager.api.dto;

import com.denver7074.taskmanager.domain.Person;
import com.denver7074.taskmanager.domain.TaskPriority;
import com.denver7074.taskmanager.domain.TaskStatus;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record TaskDto(
        @NotBlank
        String header,
        @NotBlank
        String description,
        @NotBlank
        LocalDate dateExecution,
        TaskPriority taskPriority,
        TaskStatus taskStatus,
        Person executor) {
}
