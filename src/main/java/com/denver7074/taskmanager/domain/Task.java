package com.denver7074.taskmanager.domain;

import com.denver7074.taskmanager.domain.common.IdentityEntity;

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
public class Task extends IdentityEntity {

    @Schema(description = "Заголовок задачи")
    String header;
    @Schema(description = "Описание задачи")
    String description;
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
}
