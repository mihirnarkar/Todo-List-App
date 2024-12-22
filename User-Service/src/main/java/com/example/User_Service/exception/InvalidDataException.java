package com.example.User_Service.exception;

public class InvalidDataException extends RuntimeException {

    public InvalidDataException(String mssg){
        super(mssg);
    }
    
}
