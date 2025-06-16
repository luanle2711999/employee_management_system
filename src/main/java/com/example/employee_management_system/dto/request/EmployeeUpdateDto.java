package com.example.employee_management_system.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeUpdateDto extends EmployeeCreationDto {
    String id;
}
