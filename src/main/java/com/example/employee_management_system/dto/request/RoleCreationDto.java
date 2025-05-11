package com.example.employee_management_system.dto.request;

import com.example.employee_management_system.annotation.AllUppercase;
import com.example.employee_management_system.annotation.Uppercase;
import com.example.employee_management_system.enums.PermissionName;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleCreationDto {
    @NotNull
    @Uppercase
    String name;

    String description;

    @AllUppercase
    Set<String> permissions;
}
