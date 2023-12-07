package com.denver7074.taskmanager.domain;

import com.denver7074.taskmanager.domain.common.DateEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@FieldNameConstants
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task extends DateEntity {

    @NotBlank
    @Schema(description = "Заголовок задачи")
    String header;
    @NotBlank
    @Schema(description = "Описание задачи")
    String description;
    @NotNull
    @Schema(description = "Срок выполнения")
    LocalDate dateExecution;
    @ManyToOne
    @JoinColumn(name = "priority_id")
    @Schema(description = "Уровень значимости")
    TaskPriority taskPriority;
    @ManyToOne
    @JoinColumn(name = "status_id")
    @Schema(description = "Статус задачи")
    TaskStatus taskStatus;
    @ManyToOne
    @JoinColumn(name = "author_id")
    Person author;
    @ManyToOne
    @JoinColumn(name = "executor_id")
    Person executor;
}
