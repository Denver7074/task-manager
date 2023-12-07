package com.denver7074.taskmanager.domain;

import com.denver7074.taskmanager.domain.common.DateEntity;
import com.denver7074.taskmanager.domain.common.IdentityEntity;
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
import java.util.HashSet;
import java.util.Set;

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
    @Schema(description = "Пароль")
    String password;
    @NotNull
    @Schema(description = "Дата рождения")
    LocalDate dateBirth;
    @OneToMany(mappedBy = "author")
    @Schema(description = "Спиок моих задач")
    Set<Task> myTask = new HashSet<>();
    @OneToMany(mappedBy = "executor")
    @Schema(description = "Список задач на выполнение")
    Set<Task> perform = new HashSet<>();

}
