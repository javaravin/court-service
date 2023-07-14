package com.court.exception;

public class CourtEntityNotFoundException extends RuntimeException {

    private String messageCode;
    public CourtEntityNotFoundException(String message, String messageCode){
        super(message);
        this.messageCode =messageCode;
    }
    public String getMessageCode() {
        return this.messageCode;
    }
}
