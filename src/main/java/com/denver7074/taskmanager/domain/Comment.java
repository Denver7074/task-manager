package com.denver7074.taskmanager.domain;

import com.denver7074.taskmanager.domain.common.IdentityEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comment extends IdentityEntity {

    String value;
    @OneToOne
    Person person;
    @ManyToOne
    Task task;

}
