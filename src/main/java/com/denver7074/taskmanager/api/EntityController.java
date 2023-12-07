package com.denver7074.taskmanager.api;

import com.denver7074.taskmanager.domain.Task;
import com.denver7074.taskmanager.service.CrudService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
public class EntityController {

    @RestController
    @RequestMapping("/task")
    public static class TaskController extends AbstractController<Task, Task> {
        public TaskController(CrudService crudService) {
            super(crudService);
        }

    }
}
