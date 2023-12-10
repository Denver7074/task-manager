package com.denver7074.taskmanager.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PagingResult {

    @Schema(description = "Номер страницы")
    private Integer pageNumber;
    @Schema(description = "Общее количество страниц")
    private Integer pageTotal;
    @Schema(description = "Размер страницы в выборке")
    private Integer pageSize;
    @Schema(description = "Общее количество элементов в выборке")
    private Long totalItemsCount;

    public static PagingResult createPage(Page<?> page) {
        PagingResult p = new PagingResult();
        p.setTotalItemsCount(page.getTotalElements());
        p.setPageTotal(page.getTotalPages());
        p.setPageNumber(page.getNumber());
        p.setPageSize(page.getSize());
        return p;
    }
}
