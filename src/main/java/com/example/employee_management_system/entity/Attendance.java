package com.example.employee_management_system.entity;

import com.example.employee_management_system.enums.AttendanceStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "check_in")
    LocalTime checkIn;

    @Column(name = "check_out")
    LocalTime checkOut;

    @Column(name = "date")
    LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    AttendanceStatus status;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    Employee employee;

}
