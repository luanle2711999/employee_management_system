package com.example.employee_management_system.exception;

public class DeactivatedException extends RuntimeException {
    public DeactivatedException(String message) {
        super(message);
    }
}
