package com.example.employee_management_system.service;

import com.example.employee_management_system.dto.request.PermissionCreationDto;
import com.example.employee_management_system.entity.Permission;

import java.util.List;
import java.util.Set;

public interface PermissionService {
    Permission createPermission(PermissionCreationDto permissionCreationDto);

    Set<Permission> findPermissionsByNames(Set<String> permissionNames);

    List<Permission> findAllPermissions();

    Set<Permission> findMyPermissions(String token);
}
