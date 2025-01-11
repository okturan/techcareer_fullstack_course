package com.okanerturan.techcareer_java_bootcamp.exception;

public class DuplicateTodoException extends RuntimeException {
    public DuplicateTodoException() {
        super("A todo with the same title and details field already exists");
    }
}