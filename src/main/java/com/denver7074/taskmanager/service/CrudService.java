package com.denver7074.taskmanager.service;

import com.denver7074.taskmanager.domain.common.IdentityEntity;
import com.denver7074.taskmanager.domain.common.ReachableDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.util.CastUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.denver7074.taskmanager.utils.Errors.E001;
import static java.util.stream.Collectors.toMap;

@Getter
@Service
@Transactional
@RequiredArgsConstructor
public class CrudService {

    private final ObjectMapper objectMapper;
    private final EntityManager entityManager;


    public <E extends IdentityEntity> E create(E entity) {
        IdentityEntity merge = entityManager.merge(entity);
        return CastUtils.cast(merge);
    }

    public <E extends IdentityEntity, D extends ReachableDTO> E update(D dto, Long id) {
        IdentityEntity reach = dto.reach(this);
        IdentityEntity target = findById(reach.getClass(), id);
        BeanUtils.copyProperties(target, reach);
        entityManager.persist(reach);
        return CastUtils.cast(target);
    }

    public <E, ID> E findById(Class<E> clazz, ID id) {
        return Optional.ofNullable(entityManager.find(clazz, id))
                .orElseThrow(() -> E001.thr(clazz.getSimpleName(), id));
    }
}
