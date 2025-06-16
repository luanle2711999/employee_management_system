package com.example.employee_management_system.service;

import com.example.employee_management_system.dto.request.DepartmentCreationDto;
import com.example.employee_management_system.entity.Department;

public interface DepartmentService {
    Department createDepartment(DepartmentCreationDto departmentCreationDto);

    Department findDepartmentById(String departmentId);
}
