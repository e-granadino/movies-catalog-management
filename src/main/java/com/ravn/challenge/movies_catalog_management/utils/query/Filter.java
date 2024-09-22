package com.ravn.challenge.movies_catalog_management.utils.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Filter {
    @Schema(example = "synopsis")
    private String field;

    @Schema(example = "LIKE")
    private Operator operator;

    @Schema(example = "mystery")
    private Object value;

    @Schema(example = "AND")
    private String condition = "AND";

    public Filter() {
        super();
    }

    /**
     * Creates a filter  with an "AND" condition
     * @param field
     * @param operator
     * @param value
     */
    public Filter(String field, Operator operator, Object value) {
        super();
        this.field = field;
        this.operator = operator;
        this.value = value;
    }

    /***
     * Allows to define "OR" conditions instead "AND"
     * @param field
     * @param operator
     * @param value
     * @param condition
     */
    public Filter(String field, Operator operator, Object value, String condition) {
        super();
        this.field = field;
        this.operator = operator;
        this.value = value;
        this.condition = condition;
    }
}
