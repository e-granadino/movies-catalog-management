package com.ravn.challenge.movies_catalog_management.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ravn.challenge.movies_catalog_management.utils.query.Pagination;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GenericRestListResponse<T> {
    /** The list of records. */
    private List<T> records = new ArrayList<>();

    private Pagination pagination;

    /** The message. */
    private String message;

    /**The status: Success or Fail*/
    private String status;

    private Integer statusCode;

    @Schema(hidden = true)
    public void setPaginationFromPageResult(Page<T> page){
        if((page.getNumber() + 1) <= page.getTotalPages() ){
            this.pagination = new Pagination();
            this.pagination.setCurrentPage(page.getNumber() + 1);
            this.pagination.setFirst(page.isFirst());
            this.pagination.setHasNext(page.hasNext());
            this.pagination.setHasPrevious(page.hasPrevious());
            this.pagination.setLast(page.isLast());
            this.pagination.setMaxResults(page.getNumberOfElements());
            this.pagination.setTotal(page.getTotalElements());
            this.pagination.setTotalPages(page.getTotalPages());
        }
    }
}
