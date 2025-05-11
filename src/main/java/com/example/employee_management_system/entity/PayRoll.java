package com.example.employee_management_system.entity;

import com.example.employee_management_system.enums.PayStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "pay_roll")
public class PayRoll {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    Employee employee;

    @Column(name = "pay_period_start")
    LocalDate payPeriodStart;

    @Column(name = "pay_period_end")
    LocalDate payPeriodEnd;

    @Column(name = "base_salary")
    BigDecimal baseSalary;

    @Column(name = "bonus")
    BigDecimal bonus;

    @Column(name = "deductions")
    BigDecimal deductions;

    @Column(name = "tax")
    BigDecimal tax;

    @Column(name = "net_pay", nullable = false)
    BigDecimal netPay;

    @Enumerated(EnumType.STRING)
    @Column(name = "pay_status", nullable = false)
    PayStatus payStatus;

    @Column(name = "pay_at")
    LocalDateTime payAt;

    @Column(name = "remarks")
    String remarks;
}
