package com.example.employee_management_system.service;

import com.example.employee_management_system.dto.request.DepartmentCreationDto;
import com.example.employee_management_system.entity.Department;
import com.example.employee_management_system.exception.BadRequestException;
import com.example.employee_management_system.mapper.DepartmentMapper;
import com.example.employee_management_system.repository.DepartmentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DepartmentService {
    DepartmentRepository departmentRepository;
    DepartmentMapper departmentMapper;

    public Department createDepartment(DepartmentCreationDto departmentCreationDto) {
        Department newDepartment = departmentMapper.toDepartment(departmentCreationDto);
        return this.departmentRepository.save(newDepartment);
    }

    public Department findDepartmentById(String departmentId) {
        return departmentRepository.findById(departmentId)
                                   .orElseThrow(() -> new BadRequestException("Department not found!"));
    }
}
