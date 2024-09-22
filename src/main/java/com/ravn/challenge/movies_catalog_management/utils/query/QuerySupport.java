package com.ravn.challenge.movies_catalog_management.utils.query;

import com.ravn.challenge.movies_catalog_management.utils.Constants;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class QuerySupport {

    public static PageRequest getPageRequest(SearchCriteria searchCriteria) {

        int page = searchCriteria.getPage();
        int maxResults = searchCriteria.getMaxResults();
        String sort = searchCriteria.getSort();
        String sortBy = searchCriteria.getSortBy();

        int innerPage = (page > 0) ? (page - 1) : 0;;
        int innerMaxResults = (maxResults > 0) ? maxResults : Constants.DEFAULT_MAX_RESULTS;

        PageRequest request = PageRequest.of(innerPage, innerMaxResults);

        if (sort != null && sortBy != null) {
            if (sort.equalsIgnoreCase(Constants.ASC)) {
                request.withSort(Sort.Direction.ASC, sortBy);
            } else if (sort.equalsIgnoreCase(Constants.DESC)) {
                request.withSort(Sort.Direction.DESC, sortBy);
            }
        }

        return request;
    }

    /**
     * Create a proper query SQL for the performed search
     *
     * @param searchCriteria
     * @return
     */
    public static <T> Specification<T> compileQuery(SearchCriteria searchCriteria) {
        List<CustomJpaSpecification<T>> specs = new ArrayList<>();
        // Generating Predicates for filters
        for (Filter filter : searchCriteria.getFilters()) {
            specs.add(new CustomJpaSpecification<>(filter));
        }

        if (specs.isEmpty()) {
            return Specification.where(null);
        }

        Specification<T> result = specs.get(0);
        String condition = searchCriteria.getFilters().get(0).getCondition();
        for (int i = 1; i < specs.size(); i++) {
            if (condition.equalsIgnoreCase(Constants.OR_CONDITION)) {
                result = Specification.where(result).or(specs.get(i));
            } else {
                result = Specification.where(result).and(specs.get(i));
            }
            condition = searchCriteria.getFilters().get(i).getCondition();
        }

        return result;
    }
}
