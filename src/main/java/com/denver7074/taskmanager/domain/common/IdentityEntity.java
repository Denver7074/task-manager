package com.denver7074.taskmanager.domain.common;

import com.denver7074.taskmanager.service.CrudService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
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
public abstract class IdentityEntity implements ReachableDTO {

    @Id
    @Schema(description = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Schema(description = "Дата создания", hidden = true)
    @CreatedDate
    LocalDateTime createdDate;
    @Schema(description = "Дата последнего обновления", hidden = true)
    @LastModifiedDate
    LocalDateTime lastModifiedDate;

    @Override
    public IdentityEntity reach(CrudService crudService) {
        validate(crudService);
        return this;
    }
}
