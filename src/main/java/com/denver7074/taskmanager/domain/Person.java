package com.denver7074.taskmanager.domain;

import com.denver7074.taskmanager.domain.common.DateEntity;
import com.denver7074.taskmanager.service.CrudService;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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

import static com.denver7074.taskmanager.utils.Constants.pattern;
import static com.denver7074.taskmanager.utils.Errors.E004;
import static org.apache.commons.lang3.BooleanUtils.isFalse;

@Data
@Entity
@FieldNameConstants
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Person extends DateEntity {

    @NotBlank
    @Schema(description = "e-mail почта")
    String email;
    @NotBlank
    @Schema(description = "Фамилия")
    String lastName;
    @NotBlank
    @Schema(description = "Имя")
    String firstName;
    @Schema(description = "Отчество")
    String secondName;
    @NotBlank
    @JsonIgnore
    @Schema(description = "Пароль")
    String password;
    @NotNull
    @Schema(description = "Дата рождения")
    LocalDate dateBirth;
    @OneToMany(mappedBy = "author")
    List<Task> taskList = new ArrayList<>();

    @Override
    public void validate(CrudService crudService) {
        E004.thr(isFalse(pattern.matcher(getEmail()).matches()));
    }

//    @Override
//    public void reachTransient(CrudService crudService) {
//        List<Task> tasks = crudService
//                .find(Task.class,
//                        Collections.singletonMap(Task.Fields.author, getId()),
//                        Pageable.unpaged())
//                .getContent();
//        this.setTaskList(tasks);
//    }
}
