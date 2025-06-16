package com.example.employee_management_system.controller;

import com.example.employee_management_system.common.ApiResponse;
import com.example.employee_management_system.dto.request.PermissionCreationDto;
import com.example.employee_management_system.entity.Permission;
import com.example.employee_management_system.service.impl.PermissionServiceImpl;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/permission")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {
    PermissionServiceImpl permissionServiceImpl;

    @PostMapping
    public ApiResponse<Permission> createPermission(@RequestBody @Valid PermissionCreationDto permissionCreationDto) {
        Permission newPermission = permissionServiceImpl.createPermission(permissionCreationDto);
        return ApiResponse.<Permission>builder()
                          .data(newPermission)
                          .message("Permission created successfully!")
                          .status(200)
                          .build();
    }

    @GetMapping
    public ApiResponse<List<Permission>> getAllPermissions() {
        List<Permission> permissions = permissionServiceImpl.findAllPermissions();
        return ApiResponse.<List<Permission>>builder().data(permissions).status(200).message("Get permissions successfully!").build();
    }

    @GetMapping("/my_permissions")
    public ApiResponse<Set<Permission>> getMyPermissions(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.startsWith("Bearer ") ? authHeader.substring(7) : authHeader;
        Set<Permission> permissions = permissionServiceImpl.findMyPermissions(token);
        return ApiResponse.<Set<Permission>>builder().message("Get permissions successfully!").status(200).data(permissions).build();
    }
}
