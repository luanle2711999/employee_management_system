package com.example.employee_management_system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "token_log")
public class TokenLog {
    @Id
    @Column(name = "token_id")
    String tokenId;

    @Column(name = "is_active")
    Boolean isActive;
}
