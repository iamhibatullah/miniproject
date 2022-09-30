package com.itsupport.ticketing.exception;

public class ComplainerHaveNoTicketException extends RuntimeException{
    public ComplainerHaveNoTicketException(String message) {
        super(message);
    }
}
