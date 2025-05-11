package com.example.employee_management_system.exception;

public class UnauthenticatedErrorException extends RuntimeException {
    public UnauthenticatedErrorException(String message) {
        super(message);
    }
}
