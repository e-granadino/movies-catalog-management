package com.ravn.challenge.movies_catalog_management.utils.query;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SearchCriteria {
    /** The page. */
    int page = 1;

    /** The max results. */
    int maxResults = 15;

    /** The sort. */
    String sort;

    /** The sort by. */
    String sortBy;

    /** The filters. */
    List<Filter> filters = new ArrayList<Filter>();

    /** The distinct. */
    Boolean distinct = false;

    /**
     * Create a filter with an "AND" condition as default
     * @param field
     * @param operator
     * @param value
     */
    public void addFilter(String field, Operator operator, Object value) {
        this.getFilters().add(new Filter(field, operator, value));
    }

    /**
     * Allows to create filter giving the condition "OR"
     * @param field
     * @param operator
     * @param value
     * @param condition
     */
    public void addFilter(String field, Operator operator, Object value, String condition) {
        this.getFilters().add(new Filter(field, operator, value, condition));
    }
}
