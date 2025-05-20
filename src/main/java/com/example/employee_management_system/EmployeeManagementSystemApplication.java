package com.example.employee_management_system;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeManagementSystemApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();

        System.setProperty("SPRING_DATASOURCE_URL", dotenv.get("SPRING_DATASOURCE_URL"));
        System.setProperty("SPRING_DATASOURCE_USERNAME", dotenv.get("SPRING_DATASOURCE_USERNAME"));
        System.setProperty("SPRING_DATASOURCE_PASSWORD", dotenv.get("SPRING_DATASOURCE_PASSWORD"));
        System.setProperty("JWT_SIGN_KEY", dotenv.get("JWT_SIGN_KEY"));
        System.setProperty("MAIL_API_KEY", dotenv.get("MAIL_API_KEY"));
        System.setProperty("RESEND_BASE_URL", dotenv.get("RESEND_BASE_URL"));
        SpringApplication.run(EmployeeManagementSystemApplication.class, args);
    }

}
