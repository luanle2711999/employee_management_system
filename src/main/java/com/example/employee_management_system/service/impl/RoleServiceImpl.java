package com.example.employee_management_system.service.impl;

import com.example.employee_management_system.dto.request.RoleCreationDto;
import com.example.employee_management_system.entity.Permission;
import com.example.employee_management_system.entity.Role;
import com.example.employee_management_system.exception.BadRequestException;
import com.example.employee_management_system.mapper.RoleMapper;
import com.example.employee_management_system.repository.RoleRepository;
import com.example.employee_management_system.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    RoleRepository roleRepository;
    PermissionServiceImpl permissionServiceImpl;
    RoleMapper roleMapper;

    public Role createRole(RoleCreationDto roleCreationDto) {
        if (roleRepository.existsById(roleCreationDto.getName())) {
            throw new BadRequestException("Role is existing already!");
        }
        Set<Permission> permissions = permissionServiceImpl.findPermissionsByNames(roleCreationDto.getPermissions());
        Role newRole = roleMapper.toRole(roleCreationDto);
        newRole.setPermissions(permissions);
        return roleRepository.save(newRole);
    }

    public Set<Role> findRolesByNames(Set<String> roleNames) {
        List<Role> roles = roleRepository.findAllById(roleNames);
        List<String> foundRoleNames = roles.stream()
                .map(Role::getName)
                .toList();
        List<String> missingRoles = roleNames.stream()
                .filter(item -> !foundRoleNames.contains(item))
                .toList();
        if (!missingRoles.isEmpty()) {
            throw new BadRequestException("Could not find roles" + missingRoles.toString());
        }
        return new HashSet<>(roles);
    }
}
