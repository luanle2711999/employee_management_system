package com.example.employee_management_system.controller;

import com.example.employee_management_system.common.ApiResponse;
import com.example.employee_management_system.dto.request.PayRollCreationDto;
import com.example.employee_management_system.entity.PayRoll;
import com.example.employee_management_system.service.impl.PayRollServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("payroll")
@RequiredArgsConstructor
public class PayRollController {
    PayRollServiceImpl payRollServiceImpl;

    public ApiResponse<PayRoll> createPayRoll(@RequestBody @Valid PayRollCreationDto payRollCreationDto) {
        PayRoll payRoll = payRollServiceImpl.createPayRoll(payRollCreationDto);
        return ApiResponse.<PayRoll>builder().data(payRoll).status(201).message("Payroll created successfully!").build();
    }
}
