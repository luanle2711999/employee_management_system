package com.example.employee_management_system.service;

import com.example.employee_management_system.dto.request.EmployeeCreationDto;
import com.example.employee_management_system.dto.request.EmployeeUpdateDto;
import com.example.employee_management_system.entity.Employee;


public interface EmployeeService {
    Employee createEmployee(EmployeeCreationDto employeeCreationDto);

    Employee updateEmployee(EmployeeUpdateDto employeeUpdateDto);

    Employee findEmployeeById(String employeeId);

    Employee deleteEmployeeById(String employeeId);
}
