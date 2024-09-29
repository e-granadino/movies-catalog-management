package com.ravn.challenge.movies_catalog_management.utils.query;

public enum Operator {
    IN, NOT_IN, EQUALS, LIKE, NOT_EQUALS;

    Operator() {}

    public static Operator fromString(String paramString) {
        if (paramString != null) {
            for (Operator localOperator : values()) {
                if (localOperator.name().equalsIgnoreCase(paramString)) {
                    return localOperator;
                }
            }
        }
        return null;
    }
}
