package com.itsupport.ticketing.exception;

public class TicketDoesNotHavePersonelException extends RuntimeException{
    public TicketDoesNotHavePersonelException(String message) {
        super(message);
    }
}
