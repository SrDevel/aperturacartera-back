package com.registrocartera.exceptions;

public class IntegrityValidation extends RuntimeException{
    public IntegrityValidation(String message) {
        super(message);
    }
}
