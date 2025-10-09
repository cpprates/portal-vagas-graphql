package com.graphqljava.tutorial.portal_vagas_graphql.domain.exception;

public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String message) {
        super(message);
    }
}
