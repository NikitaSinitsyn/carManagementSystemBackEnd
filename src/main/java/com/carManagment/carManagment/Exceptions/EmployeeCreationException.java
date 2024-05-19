package com.carManagment.carManagment.Exceptions;

public class EmployeeCreationException extends RuntimeException {

    public EmployeeCreationException(String message) {
        super(message);
    }

    public EmployeeCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}