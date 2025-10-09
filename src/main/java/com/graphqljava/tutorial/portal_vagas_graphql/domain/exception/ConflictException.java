package com.graphqljava.tutorial.portal_vagas_graphql.domain.exception;

public class ConflictException extends RuntimeException {

    public ConflictException(String message) {
        super(message);
    }
}
