package com.example.employee_management_system.controller;

import com.example.employee_management_system.common.ApiResponse;
import com.example.employee_management_system.dto.request.RoleCreationDto;
import com.example.employee_management_system.entity.Role;
import com.example.employee_management_system.service.RoleService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;

    @PostMapping
    public ApiResponse<Role> createRole(@RequestBody @Valid RoleCreationDto roleCreationDto) {
        Role newRole = roleService.createRole(roleCreationDto);
        return ApiResponse.<Role>builder()
                          .data(newRole)
                          .status(200)
                          .message("Role created successfully!")
                          .build();
    }
}
