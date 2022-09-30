package com.itsupport.ticketing.exception;

public class SubjectDoesNotMatchException extends RuntimeException{
    public SubjectDoesNotMatchException(String message) {
        super(message);
    }
}
