package com.example.employee_management_system.service;

import com.example.employee_management_system.dto.request.EmployeeCreationDto;
import com.example.employee_management_system.entity.Department;
import com.example.employee_management_system.entity.Employee;
import com.example.employee_management_system.exception.NotFoundException;
import com.example.employee_management_system.mapper.EmployeeMapper;
import com.example.employee_management_system.repository.EmployeeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeService {
    EmployeeRepository employeeRepository;
    DepartmentService departmentService;
    EmployeeMapper employeeMapper;

    public Employee createEmployee(EmployeeCreationDto employeeCreationDto) {
        Department existingDepartment = departmentService.findDepartmentById(employeeCreationDto.getDepartmentId());
        Employee newEmployee = employeeMapper.toEmployee(employeeCreationDto);
        newEmployee.setDepartment(existingDepartment);
        return employeeRepository.save(newEmployee);
    }

    public Employee findEmployeeById(String employeeId) {
        return employeeRepository.findById(employeeId)
                                 .orElseThrow(() -> new NotFoundException("Employee not found!"));
    }
}
