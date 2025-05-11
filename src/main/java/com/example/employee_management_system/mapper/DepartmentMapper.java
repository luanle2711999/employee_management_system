package com.example.employee_management_system.mapper;

import com.example.employee_management_system.dto.request.DepartmentCreationDto;
import com.example.employee_management_system.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    @Mapping(target = "employees", ignore = true)
    Department toDepartment(DepartmentCreationDto departmentCreationDto);
}
