package com.example.employee_management_system.mapper;

import com.example.employee_management_system.dto.request.PermissionCreationDto;
import com.example.employee_management_system.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionCreationDto permissionCreationDto);
}
