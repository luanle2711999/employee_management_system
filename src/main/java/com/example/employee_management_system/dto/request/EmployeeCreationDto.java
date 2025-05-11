package com.example.employee_management_system.dto.request;

import com.example.employee_management_system.annotation.DobConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class EmployeeCreationDto {
    @NotNull
    String firstName;

    String lastName;

    @DobConstraint(min = 18)
    LocalDate dob;

    @Email
    @NotNull
    String email;

    String phoneNumber;

    String address;

    String jobTitle;

    @NotNull
    String departmentId;

    @NotNull
    LocalDate dateOfJoining;
}
