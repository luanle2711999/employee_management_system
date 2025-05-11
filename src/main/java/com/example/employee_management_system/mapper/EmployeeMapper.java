package com.example.employee_management_system.mapper;

import com.example.employee_management_system.dto.request.EmployeeCreationDto;
import com.example.employee_management_system.dto.response.EmployeeResponseDto;
import com.example.employee_management_system.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "attendances", ignore = true)
    @Mapping(target = "payRolls", ignore = true)
    @Mapping(target = "devices", ignore = true)
    Employee toEmployee(EmployeeCreationDto dto);

    EmployeeResponseDto toEmployeeResponse(Employee employee);
}
