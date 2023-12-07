package com.denver7074.taskmanager.domain.common;

import java.io.Serializable;

public interface GenericIdProjection<ID> extends Serializable {

    ID getId();
}
