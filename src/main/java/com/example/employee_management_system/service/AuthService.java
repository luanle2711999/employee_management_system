package com.example.employee_management_system.service;

import com.example.employee_management_system.common.ApiResponse;
import com.example.employee_management_system.dto.request.LoginRequestDto;
import com.example.employee_management_system.dto.response.LoginResponseDto;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface AuthService {
    LoginResponseDto login(LoginRequestDto loginRequestDto);

    ApiResponse<Boolean> introspect(String token) throws JOSEException, ParseException;

    LoginResponseDto refreshToken(String token) throws ParseException;

}
