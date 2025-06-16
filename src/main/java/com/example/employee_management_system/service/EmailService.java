package com.example.employee_management_system.service;

public interface EmailService {
    void sendEmail(String to, String subject, String htmlContent);
}
