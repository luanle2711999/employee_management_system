package com.example.employee_management_system.dto.request;

import com.example.employee_management_system.annotation.Uppercase;
import com.example.employee_management_system.enums.PermissionName;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionCreationDto {
    @NotNull
    @Uppercase
    String name;

    String description;
}
