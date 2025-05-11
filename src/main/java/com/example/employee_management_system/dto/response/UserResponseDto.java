package com.example.employee_management_system.dto.response;

import com.example.employee_management_system.entity.Employee;
import com.example.employee_management_system.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserResponseDto {
    String id;

    String username;

    Set<Role> roles;

    boolean isActive;

    EmployeeResponseDto employee;
}
