package com.example.employee_management_system.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "dob")
    LocalDate dob;

    @Column(name = "email")
    String email;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "address")
    String address;

    @Column(name = "job_title")
    String jobTitle;

    @ManyToOne
    @JoinColumn(name = "department_id")
    Department department;

    @Column(name = "date_of_joining")
    LocalDate dateOfJoining;

    @OneToOne(mappedBy = "employee")
    User user;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    List<Attendance> attendances;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    List<PayRoll> payRolls;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    List<Device> devices;
}
