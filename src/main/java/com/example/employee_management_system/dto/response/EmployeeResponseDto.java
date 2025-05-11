package com.example.employee_management_system.dto.response;

import com.example.employee_management_system.annotation.DobConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeResponseDto {
    String firstName;

    String lastName;

    LocalDate dob;

    String email;

    String phoneNumber;

    String address;

    String jobTitle;

    DepartmentResponseDto department;

    LocalDate dateOfJoining;
}
