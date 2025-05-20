package com.example.employee_management_system.dto.request;

import com.example.employee_management_system.enums.PayStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PayRollCreationDto {
    String employeeId;
    LocalDate payPeriodStartDate;
    LocalDate payPeriodEndDate;
    BigDecimal baseSalary;
    BigDecimal tax;
    BigDecimal deductions;
    BigDecimal bonus;
    BigDecimal netPay;
    PayStatus payStatus;
    LocalDateTime payAt;
    String remarks;
}
