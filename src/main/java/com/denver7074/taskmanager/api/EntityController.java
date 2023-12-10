package com.denver7074.taskmanager.api;

import com.denver7074.taskmanager.api.dto.ExecutorDto;
import com.denver7074.taskmanager.api.dto.PersonResponseDto;
import com.denver7074.taskmanager.api.dto.TaskRequestDto;
import com.denver7074.taskmanager.api.dto.TaskResponseDto;
import com.denver7074.taskmanager.api.response.PositiveResponse;
import com.denver7074.taskmanager.api.response.ResponseApi;
import com.denver7074.taskmanager.domain.*;
import com.denver7074.taskmanager.service.CrudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import static com.denver7074.taskmanager.utils.Errors.E003;
import static java.util.Objects.nonNull;

@Component
public class EntityController {

    @RestController
    @RequestMapping("/task")
    @FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
    public static class TaskController extends AbstractController<Task, Task> {

        public TaskController(CrudService crudService) {
            super(Task.class, Task.class, crudService);
        }

        /**
         * Может создавать любой авторизованный пользователь
         */
        @PostMapping
        @Operation(description = "Метод создания задачи",
                summary = "Метод создания задачи",
                responses = {
                        @ApiResponse(responseCode = "E001", description = "Сущность ... с id ... не найдена"),
                        @ApiResponse(responseCode = "E002", description = "Отсутствуют обязательные параметры: ...")
                })
        public PositiveResponse<TaskResponseDto> createTask(@RequestParam(required = true) Long authorId, @RequestBody TaskRequestDto dto) {
            Person author = crudService.findById(Person.class, authorId);
            Task map = crudService.toMap(Task.class, dto);
            map.setAuthor(author);
            Task task = crudService.create(map);
            return ResponseApi.positiveResponse(crudService.toMap(TaskResponseDto.class, task));
        }

        @PostMapping("/executur")
        @Operation(description = "Метод добавления исполнителя",
                summary = "Метод добавления исполнителя",
                responses = {
                        @ApiResponse(responseCode = "E001", description = "Сущность ... с id ... не найдена"),
                        @ApiResponse(responseCode = "E002", description = "Отсутствуют обязательные параметры: ...")
                })
        public PositiveResponse<Task> addExecutor(@RequestParam(required = true) Long taskId, @RequestBody List<ExecutorDto> dto) {
            Task task = crudService.findById(Task.class, taskId);
            for (ExecutorDto d : dto) {
                TaskExecutor taskExecutor = crudService.create(crudService.toMap(TaskExecutor.class, d).setTask(task));
                task.addExecutors(taskExecutor);
            }
            return ResponseApi.positiveResponse(task);
        }

//        @PatchMapping("/{id}")
//        public PositiveResponse<TaskRequestDto> update(@RequestParam(required = true) Long taskId, @RequestBody TaskRequestDto dto) {
//
//        }

    }

    @RestController
    @RequestMapping("/user")
    public static class UserController extends AbstractController<Person, PersonResponseDto> {

        public UserController(CrudService crudService) {
            super(Person.class, PersonResponseDto.class, crudService);
        }

        @PostMapping
        @Operation(description = "Метод создания пользователя (регистрация)",
                summary = "Метод создания пользователя (регистрация)",
                responses = {
                        @ApiResponse(responseCode = "E002", description = "Отсутствуют обязательные параметры: ..."),
                        @ApiResponse(responseCode = "E003", description = "Пользователь с такой электронной почтой уже существует"),
                        @ApiResponse(responseCode = "E004", description = "Некорректно введен E-mail"),
                })
        public PositiveResponse<PersonResponseDto> createUser(@Validated @RequestBody Person user) {
            user.validate(crudService);
            Person uniqEmail = crudService.find(Person.class, Collections.singletonMap(Person.Fields.email, user.getEmail()));
            E003.thr(nonNull(uniqEmail));
            return ResponseApi
                    .positiveResponse(crudService.toMap(PersonResponseDto.class, crudService.create(crudService.toMap(Person.class, user))));
        }

    }


    @RestController
    @RequiredArgsConstructor
    @RequestMapping("/status")
    public static class StatusController {

        private final CrudService crudService;

        /**
         * Могут менять авторы и исполнители
         */
//        @PatchMapping("/{id}")
//        @Operation(description = "Метод обновления статуса у задачи",
//                summary = "Метод обновления статуса у задачи",
//                responses = {
//                        @ApiResponse(responseCode = "E001", description = "Сущность ... с id ... не найдена")
//                })
//        public PositiveResponse<TaskExecutor> updateStatus(@RequestParam(required = true) Long taskId, @RequestParam(required = true) Long statusId) {
//            TaskExecutor task = crudService.findByFields(TaskExecutor.class, Collections.singletonMap(TaskExecutor.Fields.task, taskId));
//            TaskStatus status = crudService.findById(TaskStatus.class, statusId);
//            task.setTaskStatus(status);
//            return ResponseApi.positiveResponse(crudService.update(task));
//        }

    }

    @RestController
    @RequestMapping("/comment")
    public static class CommentController {

    }

    @RestController
    @RequestMapping("/auth")
    public static class AuthController {

    }

}
