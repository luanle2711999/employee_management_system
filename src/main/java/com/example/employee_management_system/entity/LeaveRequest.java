package com.example.employee_management_system.entity;

import com.example.employee_management_system.enums.LeaveStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "leave_request")
public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "start_date", nullable = false)
    LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    LocalDateTime endDate;

    @Column(name = "reason", length = 500, nullable = false)
    String reason;

    @Enumerated(EnumType.STRING)
    @Column(name = "leave_status", nullable = false)
    LeaveStatus leaveStatus;

    @Column(name = "applied_at", nullable = false, updatable = false)
    LocalDateTime appliedAt;


    @Column(name = "decision_at")
    LocalDateTime decisionAt;

    @Column(length = 500)
    String decisionNote;

    @ManyToOne
    @JoinColumn(name = "approved_by_id")
    User approvedBy;

    @ManyToOne
    @JoinColumn(name = "leave_type_id", nullable = false)
    LeaveType leaveType;
}
