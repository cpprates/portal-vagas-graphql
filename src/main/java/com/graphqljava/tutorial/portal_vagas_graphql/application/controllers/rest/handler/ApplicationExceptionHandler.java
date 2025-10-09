//package com.graphqljava.tutorial.portal_vagas_graphql.application.controllers.rest.handler;
//
//import com.graphqljava.tutorial.portal_vagas_graphql.domain.exception.ConflictException;
//import com.graphqljava.tutorial.portal_vagas_graphql.domain.exception.DataNotFoundException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import static org.springframework.http.ResponseEntity.notFound;
//import static org.springframework.http.ResponseEntity.status;
//
//@ControllerAdvice
//public class ApplicationExceptionHandler {
//
//    @ExceptionHandler(DataNotFoundException.class)
//    public ResponseEntity<ResponseMessages> handleDataNotFoundException(DataNotFoundException exception) {
//        if (exception.getMessage() == null) {
//            return notFound().build();
//        }
//        return status(HttpStatus.NOT_FOUND).body(buildMessages(exception.getMessage()));
//    }
//
//    @ExceptionHandler(ConflictException.class)
//    public ResponseEntity<ResponseMessages> handleConflictException(ConflictException exception) {
//        return status(HttpStatus.CONFLICT).body(buildMessages(exception.getMessage()));
//    }
//
//    private ResponseMessages buildMessages(String message) {
//        ResponseMessages responseMessages = new ResponseMessages();
//        responseMessages.addMessage(message);
//
//        return responseMessages;
//    }
//}
