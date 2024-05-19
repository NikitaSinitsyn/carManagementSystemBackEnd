package com.carManagment.carManagment.Exceptions;

public class RepairNotFoundException extends RuntimeException {
    public RepairNotFoundException(String message) {
        super(message);
    }
}