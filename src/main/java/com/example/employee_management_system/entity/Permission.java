package com.example.employee_management_system.entity;

import com.example.employee_management_system.annotation.Uppercase;
import com.example.employee_management_system.enums.PermissionName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "permission")
public class Permission {
    @Id
    @Uppercase
    String name;

    @Column(name = "description")
    String description;
}
