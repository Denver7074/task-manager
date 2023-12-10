package com.denver7074.taskmanager.api;

import com.denver7074.taskmanager.api.dto.PersonResponseDto;
import com.denver7074.taskmanager.api.response.PositiveResponse;
import com.denver7074.taskmanager.api.response.ResponseApi;
import com.denver7074.taskmanager.domain.common.IdentityEntity;
import com.denver7074.taskmanager.service.CrudService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Validated
@RequiredArgsConstructor
public class AbstractController<E extends IdentityEntity, D extends IdentityEntity> {

    @Getter
    private final Class<E> clazz;
    @Getter
    private final Class<D> clazzDto;
    protected final CrudService crudService;


    @GetMapping
    public PositiveResponse<D> findById(@RequestParam Long id) {
        E entity = crudService.findById(clazz, id);
        return ResponseApi.positiveResponse(crudService.toMap(clazzDto, entity));
    }

    @GetMapping("findAll")
    public PositiveResponse<List<D>> findAll(@SortDefault(sort = IdentityEntity.Fields.id, direction = Sort.Direction.DESC)
                                                 @Parameter(hidden = true) @PageableDefault Pageable pageable,
                                             @RequestParam(required = false) List<Long> ids,
                                             @Parameter(hidden = true) @RequestParam(required = false)
                                                 Map<String, Object> map) {
//        if (CollectionUtils.isEmpty(ids)) {
//
//        } else {
//            ;
//        }
        List<E> byId = crudService.findById(clazz, ids);
        List<D> list = byId.stream()
                .map(id -> crudService.toMap(clazzDto, id))
                .collect(Collectors.toList());
        return ResponseApi.positiveResponse(list,new PageImpl<>(list));
    }
}
