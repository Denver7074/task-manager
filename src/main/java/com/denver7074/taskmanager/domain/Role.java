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
public class Role extends IdentityEntity {

    @Schema(description = "Название роли")
    String name;
    @Schema(description = "Код роли")
    String code;

}
