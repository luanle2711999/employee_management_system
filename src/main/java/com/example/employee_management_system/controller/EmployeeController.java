package com.example.employee_management_system.controller;

import com.example.employee_management_system.common.ApiResponse;
import com.example.employee_management_system.dto.request.EmployeeCreationDto;
import com.example.employee_management_system.dto.request.EmployeeUpdateDto;
import com.example.employee_management_system.dto.response.EmployeeResponseDto;
import com.example.employee_management_system.entity.Employee;
import com.example.employee_management_system.mapper.EmployeeMapper;
import com.example.employee_management_system.service.impl.EmployeeServiceImpl;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeController {
    EmployeeServiceImpl employeeServiceImpl;
    EmployeeMapper employeeMapper;

    @PostMapping
    public ApiResponse<EmployeeResponseDto> createEmployee(@RequestBody @Valid EmployeeCreationDto employeeCreationDto) {
        Employee newEmployee = employeeServiceImpl.createEmployee(employeeCreationDto);
        EmployeeResponseDto employeeResponseDto = employeeMapper.toEmployeeResponse(newEmployee);
        return ApiResponse.<EmployeeResponseDto>builder()
                .data(employeeResponseDto)
                .message("Employee created successfully!")
                .status(200)
                .build();
    }

    @PostMapping("/update")
    public ApiResponse<EmployeeResponseDto> updateEmployee(@RequestBody @Valid EmployeeUpdateDto employeeUpdateDto) {
        Employee updatedEmployee = employeeServiceImpl.updateEmployee(employeeUpdateDto);
        EmployeeResponseDto employeeResponseDto = employeeMapper.toEmployeeResponse(updatedEmployee);
        return ApiResponse.<EmployeeResponseDto>builder()
                .data(employeeResponseDto)
                .build();
    }

    @GetMapping("/{employeeId}")
    public ApiResponse<EmployeeResponseDto> getEmployee(@PathVariable String employeeId) {
        Employee employee = employeeServiceImpl.findEmployeeById(employeeId);
        EmployeeResponseDto employeeResponseDto = employeeMapper.toEmployeeResponse(employee);
        return ApiResponse.<EmployeeResponseDto>builder()
                .data(employeeResponseDto)
                .build();
    }

    @DeleteMapping("/{employeeId}")
    public ApiResponse<EmployeeResponseDto> deleteEmployee(@PathVariable @Valid String employeeId) {
        Employee deletedEmployee = employeeServiceImpl.deleteEmployeeById(employeeId);
        EmployeeResponseDto employeeResponseDto = employeeMapper.toEmployeeResponse(deletedEmployee);
        return ApiResponse.<EmployeeResponseDto>builder()
                .data(employeeResponseDto)
                .build();
    }
}
