package com.example.User_Service.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String mssg){
        super(mssg);
    }
    
}
