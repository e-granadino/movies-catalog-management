package com.ravn.challenge.movies_catalog_management.utils;

public class Constants {
    public static final Integer DEFAULT_MAX_RESULTS = 15;
    public static final String ASC = "ASC";
    public static final String DESC = "DESC";
    public static final String SUCCESS_RESPONSE = "SUCCESS";
    public static final String FAIL_RESPONSE = "FAIL";
    public static final String OR_CONDITION = "OR";
    public static final String USER_ROLE = "user";
    public static final String ADMIN_ROLE = "admin";

    // Swagger Documentation utils
    public static final String SWAGGER_MOVIE_SEARCH_DESCRIPTION = "Searches movies that meet the filters and conditions provided, the filters are based on the movie properties(eg. title, synopsis, release year, director, or even duration); also support pagination and sort, so you can provide the page number you need, how many results, the sort(ascending and descending) field and the sort by(which field should be sorted)";
}
