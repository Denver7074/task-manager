package com.denver7074.taskmanager.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum Role {

    AUTHOR("автор"),
    EXECUTOR("исполнитель");

    String value;

}
