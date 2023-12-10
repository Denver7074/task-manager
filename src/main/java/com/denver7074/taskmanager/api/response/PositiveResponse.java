package com.denver7074.taskmanager.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.data.domain.Page;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PositiveResponse<T> extends Response<T>{

    @JsonInclude(NON_NULL)
    T data;
    @Getter
    @JsonInclude(NON_NULL)
    @Schema(description = "Параметры пагинации")
    private PagingResult pagingResult;

    public PositiveResponse(T data) {
        super(Boolean.TRUE);
        this.data = data;
    }

    public PositiveResponse<T> paged(Page<?> data) {
        this.pagingResult = PagingResult.createPage(data);
        return this;
    }
}
