package com.carManagment.carManagment.Exceptions;

public class TireNotFoundException extends RuntimeException {

    public TireNotFoundException(String message) {
        super(message);
    }
}
