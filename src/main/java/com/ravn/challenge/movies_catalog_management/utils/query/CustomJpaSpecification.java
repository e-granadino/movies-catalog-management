package com.ravn.challenge.movies_catalog_management.utils.query;

import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

@Slf4j
public class CustomJpaSpecification<T> implements Specification<T> {
    Filter filter;

    public CustomJpaSpecification(Filter filter) {
        super();
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> cQuery, CriteriaBuilder cBuilder) {
        //Note: missing scenario for nested fields
        Path<String> pathString = root.get(filter.getField());

        switch (filter.getOperator()) {
            case EQUALS:
                // Querying and comparing with Equals:
                // select * from Model where field = query
                return  cBuilder.equal(pathString, filter.getValue());
            case LIKE:
                // Querying and comparing with Like:
                // select * from Model where field like % query %
                String fieldValue = "%" + filter.getValue().toString().toLowerCase() + "%";
                return cBuilder.like(cBuilder.lower(pathString), fieldValue);
            default:
                return null;
        }
    }
}
