package com.denver7074.taskmanager.domain;

import com.denver7074.taskmanager.domain.common.IdentityEntity;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comment extends IdentityEntity {

    String value;

}
