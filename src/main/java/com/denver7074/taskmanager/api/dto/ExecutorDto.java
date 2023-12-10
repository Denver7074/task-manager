package com.denver7074.taskmanager.api.dto;

import com.denver7074.taskmanager.domain.Task;
import com.denver7074.taskmanager.domain.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExecutorDto {

    ExecutorDto.Person executor;
    LocalDate dateExecution;
    TaskStatus taskStatus;

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Person {
        @NotBlank
        Long id;
        String email;
    }

}
