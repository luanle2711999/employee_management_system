package com.example.employee_management_system.controller;

import com.example.employee_management_system.common.ApiResponse;
import com.example.employee_management_system.dto.request.EmployeeCreationDto;
import com.example.employee_management_system.dto.response.EmployeeResponseDto;
import com.example.employee_management_system.entity.Employee;
import com.example.employee_management_system.mapper.EmployeeMapper;
import com.example.employee_management_system.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeController {
    EmployeeService employeeService;
    EmployeeMapper employeeMapper;

    @PostMapping
    public ApiResponse<EmployeeResponseDto> createEmployee(@RequestBody @Valid EmployeeCreationDto employeeCreationDto) {
        Employee newEmployee = employeeService.createEmployee(employeeCreationDto);
        EmployeeResponseDto employeeResponseDto = employeeMapper.toEmployeeResponse(newEmployee);
        return ApiResponse.<EmployeeResponseDto>builder()
                          .data(employeeResponseDto)
                          .message("Employee created successfully!")
                          .status(200)
                          .build();
    }
}
