package com.ravn.challenge.movies_catalog_management.utils.query;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
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
}
