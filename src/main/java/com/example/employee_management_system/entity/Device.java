package com.example.employee_management_system.entity;

import com.example.employee_management_system.enums.DeviceStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "device")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "device_name")
    String deviceName;

    @Column(name = "device_type")
    String deviceType;

    @Column(name = "serial_number")
    String serialNumber;

    @Column(name = "model")
    String model;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    DeviceStatus status;


    @Column(name = "assigned_date")
    LocalDateTime assignedDate;

    @Column(name = "returned_date")
    LocalDateTime returnedDate;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    Employee employee;
}

