package com.sendvi41.borshchatbackend.exceptions;

public class ServiceResourceNotFoundException extends Exception{

    public ServiceResourceNotFoundException() {
        super();
    }

    public ServiceResourceNotFoundException(String message) {
        super(message);
    }
}