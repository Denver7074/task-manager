package com.denver7074.taskmanager.service;

import com.denver7074.taskmanager.domain.common.IdentityEntity;
import com.denver7074.taskmanager.utils.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.apache.commons.lang3.BooleanUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.CastUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.*;
import java.util.stream.Collectors;

import static com.denver7074.taskmanager.utils.Errors.E001;

@Getter
@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CrudService {

    ModelMapper modelMapper;
    ObjectMapper objectMapper;
    EntityManager entityManager;
    JdbcTemplate template;

    @NonFinal
    @Value("${spring.datasource.hikari.schema}")
    String schema;

    public <E, D> E toMap(Class<E> clazz, D dto) {
        return Utils.safeGet(() -> modelMapper.map(dto, clazz));
    }

    public <E, ID> E reference(Class<E> clazz, ID id) {
        return Optional.ofNullable(entityManager.getReference(clazz, id))
                .orElseThrow(() -> E001.thr(clazz.getSimpleName(), id));
    }

    public <E extends IdentityEntity> E create(E entity) {
        IdentityEntity merge = entityManager.merge(entity);
        return CastUtils.cast(merge);
    }

    public <E extends IdentityEntity, D> E update(D dto, Long id, Class<E> clazz) {
        IdentityEntity target = findById(clazz, id);
        modelMapper.map(dto, target);
        entityManager.persist(target);
        return CastUtils.cast(update(target));
    }

    public <E extends IdentityEntity> E update(E entity) {
        entityManager.persist(entity);
        return entity;
    }


    public <E, ID> List<E> findById(Class<E> clazz, List<ID> list) {
        return list.stream()
                .map(id -> findById(clazz, id))
                .collect(Collectors.toList());
    }

    public <E, ID> E findById(Class<E> clazz, ID id) {
        return Optional.ofNullable(entityManager.find(clazz, id))
                .orElseThrow(() -> E001.thr(clazz.getSimpleName(), id));
    }

    public <E> E find(Class<E> clazz, Map<String, Object> filter) {
        return find(clazz, filter, Pageable.unpaged())
                .stream()
                .findFirst()
                .orElse(null);
    }

    public <E> Page<E> find(Class<E> clazz, Map<String, Object> filter, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> query = cb.createQuery(clazz);
        Root<E> root = query.from(clazz);


        List<Predicate> predicates = new ArrayList<>();
        for (Map.Entry<String, Object> entry : filter.entrySet()) {
            String field = entry.getKey();
            Object value = entry.getValue();
            predicates.add(cb.equal(root.get(field), value));
        }

        query.where(predicates.toArray(new Predicate[0]));

        if (pageable.getSort().isSorted()) {
            List<Order> orders = new ArrayList<>();
            for (Sort.Order sortOrder : pageable.getSort()) {
                Path<Object> sortPath = root.get(sortOrder.getProperty());
                orders.add(sortOrder.isAscending() ? cb.asc(sortPath) : cb.desc(sortPath));
            }
            query.orderBy(orders);
        }
        Query jpaQuery = entityManager.createQuery(query);

        if (BooleanUtils.isTrue(pageable.isUnpaged())) {
            return new PageImpl<>(jpaQuery.getResultList());
        }

        jpaQuery.setFirstResult((int) pageable.getOffset());
        jpaQuery.setMaxResults(pageable.getPageSize());


        List<E> content = jpaQuery.getResultList();

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        countQuery.select(cb.count(countQuery.from(clazz)));
        countQuery.where(predicates.toArray(new Predicate[0]));
        Long total = entityManager.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(content, pageable, total);
    }

    public <E, ID> void delete(Class<E> clazz, ID id) {
        E entity = findById(clazz, id);
        entityManager.remove(entity);
    }
}
