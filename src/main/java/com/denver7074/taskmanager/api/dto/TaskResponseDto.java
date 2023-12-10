package com.denver7074.taskmanager.api.dto;

import com.denver7074.taskmanager.domain.TaskPriority;
import com.denver7074.taskmanager.domain.TaskStatus;
import com.denver7074.taskmanager.domain.common.IdentityEntity;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskResponseDto extends IdentityEntity {

    @NotBlank
    String header;
    Person author;
    @NotBlank
    String description;
    TaskPriority taskPriority;
    List<TaskExecutor> executors = new ArrayList<>();

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class TaskExecutor {
        @NotNull
        LocalDate dateExecution;
        TaskStatus taskStatus;
        Person executor;
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Person {
        @NotNull
        Long id;
        String email;
    }

}
