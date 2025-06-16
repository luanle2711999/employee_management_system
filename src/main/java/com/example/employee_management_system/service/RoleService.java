package com.example.employee_management_system.service;

import com.example.employee_management_system.dto.request.RoleCreationDto;
import com.example.employee_management_system.entity.Role;

import java.util.Set;

public interface RoleService {
    Role createRole(RoleCreationDto roleCreationDto);

    Set<Role> findRolesByNames(Set<String> roleNames);
}
