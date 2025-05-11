package com.example.employee_management_system.dto.request;

import com.example.employee_management_system.annotation.AllUppercase;
import com.example.employee_management_system.annotation.StrongPassword;
import com.example.employee_management_system.entity.Employee;
import com.example.employee_management_system.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreationRequestDto {
    @Size(min = 6)
    String username;

    @StrongPassword
    String password;

    @AllUppercase
    Set<String> roles;

    String employeeId;

    Boolean isActive;

}
