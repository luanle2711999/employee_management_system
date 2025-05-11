package com.example.employee_management_system.mapper;

import com.example.employee_management_system.dto.request.RoleCreationDto;
import com.example.employee_management_system.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleCreationDto roleCreationDto);
}
