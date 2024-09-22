package com.ravn.challenge.movies_catalog_management.dto;

import com.ravn.challenge.movies_catalog_management.utils.query.Filter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class QuerySearchRequestDto {

    @Schema(example = "1", minimum="1")
    private Integer page;

    @Schema(example = "10", minimum="1")
    private Integer maxResults;

    @Schema(example = "asc", description = "Supported formats are: ASC | asc | DESC | desc")
    private String sort;

    @Schema(example = "title")
    private String sortBy;

    private List<Filter> filters = new ArrayList<>();
}
