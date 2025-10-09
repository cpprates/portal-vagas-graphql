//package com.graphqljava.tutorial.portal_vagas_graphql.application.controllers.rest.handler;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//
//import java.util.HashSet;
//import java.util.Set;
//
//public class ResponseMessages {
//
//    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
//    private Set<String> messages;
//
//    public ResponseMessages() {
//        messages = new HashSet<>();
//    }
//
//    public ResponseMessages(Set<String> messages) {
//        this.messages = messages;
//    }
//
//    public Set<String> getMessages() {
//        return messages;
//    }
//
//    public void addMessage(String message) {
//        this.messages.add(message);
//    }
//}
