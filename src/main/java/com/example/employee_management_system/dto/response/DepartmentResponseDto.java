package com.example.employee_management_system.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentResponseDto {
    String id;

    String name;

    String description;
}
