package com.denver7074.taskmanager.domain;

import com.denver7074.taskmanager.domain.common.IdentityEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskStatus extends IdentityEntity {

    @Schema(description = "Название статуса")
    String name;
    @Schema(description = "Код статуса", hidden = true)
    String code;

}
