package com.example.employee_management_system.service.impl;

import com.example.employee_management_system.dto.request.EmployeeCreationDto;
import com.example.employee_management_system.dto.request.EmployeeUpdateDto;
import com.example.employee_management_system.entity.Department;
import com.example.employee_management_system.entity.Employee;
import com.example.employee_management_system.entity.User;
import com.example.employee_management_system.exception.NotFoundException;
import com.example.employee_management_system.mapper.EmployeeMapper;
import com.example.employee_management_system.repository.EmployeeRepository;
import com.example.employee_management_system.repository.UserRepository;
import com.example.employee_management_system.service.EmployeeService;
import com.example.employee_management_system.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeRepository employeeRepository;
    DepartmentServiceImpl departmentServiceImpl;
    EmployeeMapper employeeMapper;
    UserRepository userRepository;

    public Employee createEmployee(EmployeeCreationDto employeeCreationDto) {
        Department existingDepartment = departmentServiceImpl.findDepartmentById(employeeCreationDto.getDepartmentId());
        Employee newEmployee = employeeMapper.toEmployee(employeeCreationDto);
        newEmployee.setDepartment(existingDepartment);
        return employeeRepository.save(newEmployee);
    }

    public Employee updateEmployee(EmployeeUpdateDto employeeUpdateDto) {
        Employee updatingEmployee = findEmployeeById(employeeUpdateDto.getId());
        Employee updatedEmployee = employeeMapper.toUpdateEmployee(employeeUpdateDto);
        employeeRepository.save(updatingEmployee);
        return updatedEmployee;
    }

    public Employee findEmployeeById(String employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NotFoundException("Employee not found!"));
    }

    public Employee deleteEmployeeById(String employeeId) {
        Employee existingEmployee = findEmployeeById(employeeId);
        User existingUser = existingEmployee.getUser();
        if (Objects.nonNull(existingUser)) {
            userRepository.deleteById(existingUser.getId());
        }
        employeeRepository.deleteById(employeeId);
        return existingEmployee;
    }
}
