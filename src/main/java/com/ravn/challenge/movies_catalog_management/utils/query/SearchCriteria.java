package com.ravn.challenge.movies_catalog_management.utils.query;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SearchCriteria {
    /** The page. */
    private int page = 1;

    /** The max results. */
    private int maxResults = 15;

    /** The sort. */
    private String sort;

    /** The sort by. */
    private String sortBy;

    /** The filters. */
    private List<Filter> filters = new ArrayList<>();

    /** The distinct. */
    private Boolean distinct = false;

    /**
     * Create a filter with an "AND" condition as default
     * @param field This must match with the entity field
     * @param operator Valid options are: EQUALS, LIKE
     * @param value Which value should be compared in the database
     */
    public void addFilter(String field, Operator operator, Object value) {
        this.getFilters().add(new Filter(field, operator, value));
    }

    /**
     * Allows to create filter giving the condition "OR"
     * @param field This must match with the entity field
     * @param operator Valid options are: EQUALS, LIKE
     * @param value Which value should be compared in the database
     * @param condition Some of: AND,OR
     */
    public void addFilter(String field, Operator operator, Object value, String condition) {
        this.getFilters().add(new Filter(field, operator, value, condition));
    }
}
