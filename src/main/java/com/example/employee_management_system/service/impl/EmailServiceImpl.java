package com.example.employee_management_system.service.impl;

import com.example.employee_management_system.dto.request.EmailRequestDto;
import com.example.employee_management_system.service.EmailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailServiceImpl implements EmailService {

    @NonFinal
    @Value("${email.apiKey}")
    String apiKey;

    @NonFinal
    @Value("${email.baseUrl}")
    String baseUrl;

    RestTemplate restTemplate;
    public void sendEmail(String to, String subject, String htmlContent) {
        EmailRequestDto request = new EmailRequestDto();
        request.setFrom("Your Name <onboarding@resend.dev>"); // Use a verified domain
        request.setTo(to);
        request.setSubject(subject);
        request.setHtml(htmlContent);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        HttpEntity<EmailRequestDto> entity = new HttpEntity<>(request, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(
                    baseUrl + "/emails",
                    entity,
                    String.class
            );
            System.out.println("Email response: " + response.getBody());
        } catch (HttpStatusCodeException e) {
            System.err.println("Failed to send email: " + e.getResponseBodyAsString());
        }
    }

}
