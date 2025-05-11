package com.example.employee_management_system.controller;

import com.example.employee_management_system.common.ApiResponse;
import com.example.employee_management_system.dto.request.LoginRequestDto;
import com.example.employee_management_system.dto.response.LoginResponseDto;
import com.example.employee_management_system.service.AuthService;
import com.nimbusds.jose.JOSEException;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AuthController {
    AuthService authService;

    @PostMapping("/login")
    public ApiResponse<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        LoginResponseDto loginResponseDto = authService.login(loginRequestDto);
        return ApiResponse.<LoginResponseDto>builder()
                          .data(loginResponseDto)
                          .message("Log in Successfully!")
                          .status(200)
                          .data(loginResponseDto)
                          .build();
    }

    @PostMapping("/introspect")
    public ApiResponse<Boolean> introspect(@RequestHeader("Authorization") String authHeader) throws ParseException, JOSEException {
        String token = authHeader.startsWith("Bearer ") ? authHeader.substring(7) : authHeader;
        return authService.introspect(token);
    }
}
