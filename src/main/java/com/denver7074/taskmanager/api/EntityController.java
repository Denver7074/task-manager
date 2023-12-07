package com.denver7074.taskmanager.api;

import com.denver7074.taskmanager.api.dto.PersonDto;
import com.denver7074.taskmanager.api.dto.TaskDto;
import com.denver7074.taskmanager.api.response.PositiveResponse;
import com.denver7074.taskmanager.api.response.ResponseApi;
import com.denver7074.taskmanager.domain.Person;
import com.denver7074.taskmanager.domain.Task;
import com.denver7074.taskmanager.service.CrudService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
public class EntityController {

    @RestController
    @RequestMapping("/task")
    @RequiredArgsConstructor
    @FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
    public static class TaskController  {

        CrudService crudService;

        @PostMapping
        public PositiveResponse<Task> createTask(@RequestParam(required = true) Long userId, @RequestBody TaskDto dto) {
            Person author = crudService.findById(Person.class, userId);
            Task task = new Task();
            BeanUtils.copyProperties(dto, task);
            task.setAuthor(author);
            return ResponseApi.positiveResponse(crudService.create(task));
        }

    }

    @RestController
    @RequestMapping("/user")
    @RequiredArgsConstructor
    @FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
    public static class UserController {

        CrudService crudService;
        @PostMapping
        public PositiveResponse<Person> createUser(@RequestBody PersonDto user) {
            Person person = new Person();
            BeanUtils.copyProperties(user, person);
            return ResponseApi.positiveResponse(crudService.create(person));
        }

    }
}
