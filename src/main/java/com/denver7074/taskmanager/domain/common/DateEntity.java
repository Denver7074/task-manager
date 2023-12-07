package com.denver7074.taskmanager.domain.common;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@FieldNameConstants
@EntityListeners(AuditingEntityListener.class)
public abstract class DateEntity extends IdentityEntity {

    @CreatedDate
    @Schema(description = "Дата создания", hidden = true)
    LocalDateTime createdDate;
    @LastModifiedDate
    @Schema(description = "Дата последнего обновления", hidden = true)
    LocalDateTime lastModifiedDate;

}
