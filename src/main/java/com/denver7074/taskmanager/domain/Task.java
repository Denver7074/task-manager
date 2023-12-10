package com.denver7074.taskmanager.domain;

import com.denver7074.taskmanager.api.EntityController;
import com.denver7074.taskmanager.domain.common.DateEntity;
import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@FieldNameConstants
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task extends DateEntity {

    @Schema(description = "Заголовок задачи")
    String header;
    @Schema(description = "Описание задачи")
    String description;
    @ManyToOne
    @JoinColumn(name = "priority_id")
    @Schema(description = "Уровень значимости")
    TaskPriority taskPriority;
    @ManyToOne
    @JoinColumn(name = "author_id")
    Person author;
    @OneToMany(mappedBy = "task")
    @JsonManagedReference
    List<TaskExecutor> executors = new ArrayList<>();

    public void addExecutors(TaskExecutor taskExecutor) {
        this.executors.add(taskExecutor);
        taskExecutor.setTask(this);
    }

}
