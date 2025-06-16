package com.example.employee_management_system.controller;

import com.example.employee_management_system.common.ApiResponse;
import com.example.employee_management_system.dto.request.DepartmentCreationDto;
import com.example.employee_management_system.entity.Department;
import com.example.employee_management_system.service.impl.DepartmentServiceImpl;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DepartmentController {
    DepartmentServiceImpl departmentServiceImpl;

    @PostMapping
    public ApiResponse<Department> createDepartment(@RequestBody @Valid DepartmentCreationDto departmentCreationDto) {
        Department newDepartment = departmentServiceImpl.createDepartment(departmentCreationDto);
        return ApiResponse.<Department>builder()
                          .data(newDepartment)
                          .status(200)
                          .message("Create department successfully!")
                          .build();
    }
}
