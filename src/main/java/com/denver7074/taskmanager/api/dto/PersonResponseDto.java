package com.denver7074.taskmanager.api.dto;

import com.denver7074.taskmanager.domain.*;
import com.denver7074.taskmanager.domain.common.IdentityEntity;
import com.denver7074.taskmanager.service.CrudService;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@FieldNameConstants
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PersonResponseDto extends IdentityEntity {

    String email;
    String lastName;
    String firstName;
    String secondName;
    LocalDate dateBirth;
    List<Task> taskList = new ArrayList<>();

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Task {
        String header;
        String description;
        TaskPriority taskPriority;
        String role = Role.AUTHOR.getValue();
        List<TaskExecutor> listExecutor = new ArrayList<>();
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class TaskExecutor {
        Person executor;
        TaskStatus taskStatus;
        LocalDate dateExecution;
        String role = Role.EXECUTOR.getValue();
    }

}
