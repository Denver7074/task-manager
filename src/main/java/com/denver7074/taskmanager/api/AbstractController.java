package com.denver7074.taskmanager.api;


import com.denver7074.taskmanager.api.response.PositiveResponse;
import com.denver7074.taskmanager.api.response.ResponseApi;
import com.denver7074.taskmanager.domain.Person;
import com.denver7074.taskmanager.domain.common.IdentityEntity;
import com.denver7074.taskmanager.domain.common.ReachableDTO;
import com.denver7074.taskmanager.service.CrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@Component
@RequiredArgsConstructor
public class AbstractController<E extends IdentityEntity, D extends ReachableDTO> {

    private final CrudService crudService;

    @PostMapping
    public PositiveResponse<E> create(@RequestBody D dto) {
        return ResponseApi.positiveResponse(crudService.create(dto));
    }

    @PatchMapping("/{id}")
    public PositiveResponse<E> update(@RequestBody D dto, @PathVariable Long id) {
        return ResponseApi.positiveResponse(crudService.update(dto, id));
    }



}
