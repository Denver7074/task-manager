package com.denver7074.taskmanager.api.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record PersonDto(@NotBlank
                        String email,
                        @NotBlank
                        String lastName,
                        @NotBlank
                        String firstName,
                        String secondName,
                        @NotBlank
                        String password,
                        @NotBlank
                        LocalDate dateBirth) {
}
