package com.example.employee_management_system.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "audit_log")
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "entity_name")
    String entityName;

    @Column(name = "entity_id")
    String entityId;

    @Column(name = "action")
    String action;

    @Column(name = "performed_by")
    String userId;

    @Column(name = "timestamp")
    LocalDateTime timestamp;
}
