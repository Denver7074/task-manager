package com.denver7074.taskmanager.domain;

import com.denver7074.taskmanager.domain.common.IdentityEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDate;

@Data
@Entity
@FieldNameConstants
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskExecutor extends IdentityEntity {

    @ManyToOne
    @JoinColumn(name = "person_id")
    @Schema(description = "Исполнитель")
    Person executor;
    @Schema(description = "Срок выполнения")
    LocalDate dateExecution;
    @ManyToOne
    @JsonBackReference
    Task task;
    @JoinColumn(name = "status_id")
    @ManyToOne
    @Schema(description = "Статус задачи")
    TaskStatus taskStatus;

    public void addTask(Task task) {
        this.setTask(task);
        task.addExecutors(this);
    }

}
