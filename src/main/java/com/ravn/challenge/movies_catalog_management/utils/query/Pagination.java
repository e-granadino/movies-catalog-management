package com.ravn.challenge.movies_catalog_management.utils.query;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Pagination {
    private Integer currentPage;

    private Integer totalPages;

    private Integer maxResults;

    private Long total;

    private boolean first;

    private boolean last;

    private boolean hasNext;

    private boolean hasPrevious;

    public Pagination(){}
}
