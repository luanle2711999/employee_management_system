package com.example.employee_management_system.service;

import com.example.employee_management_system.dto.request.PermissionCreationDto;
import com.example.employee_management_system.entity.Permission;
import com.example.employee_management_system.exception.BadRequestException;
import com.example.employee_management_system.mapper.PermissionMapper;
import com.example.employee_management_system.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public Permission createPermission(PermissionCreationDto permissionCreationDto) {
        Permission newPermission = permissionMapper.toPermission(permissionCreationDto);
        return permissionRepository.save(newPermission);
    }

    public Set<Permission> findPermissionsByNames(Set<String> permissionNames) {
        List<Permission> permissions = permissionRepository.findAllById(permissionNames);
        List<String> foundNames = permissions.stream()
                                             .map(Permission::getName)
                                             .toList();
        List<String> missingNames = permissionNames.stream()
                                                   .filter(item -> !foundNames.contains(item))
                                                   .toList();
        if (!missingNames.isEmpty()) {
            throw new BadRequestException("Could not find roles" + missingNames.toString());

        }
        return new HashSet<>(permissions);
    }
}
