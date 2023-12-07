package com.denver7074.taskmanager.domain.common;

import com.denver7074.taskmanager.service.CrudService;

public interface ReachableDTO extends GenericIdProjection<Long>{

    IdentityEntity reach(CrudService crudService);

    default void validate(CrudService crudService) {
    }

    default void reachTransient(CrudService crudService) {
    }
    
}
