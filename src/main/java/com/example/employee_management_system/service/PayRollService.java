package com.example.employee_management_system.service;

import com.example.employee_management_system.dto.request.PayRollCreationDto;
import com.example.employee_management_system.entity.Employee;
import com.example.employee_management_system.entity.PayRoll;
import com.example.employee_management_system.mapper.PayRollMapper;
import com.example.employee_management_system.repository.PayRollRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PayRollService {
    PayRollRepository payRollRepository;
    EmployeeService employeeService;
    PayRollMapper payRollMapper;

    public PayRoll createPayRoll(PayRollCreationDto payRollCreationDto) {
        Employee employee = employeeService.findEmployeeById(payRollCreationDto.getEmployeeId());
        PayRoll payRoll = payRollMapper.toPayRoll(payRollCreationDto);
        payRoll.setEmployee(employee);
        payRollRepository.save(payRoll);
        return payRoll;
    }
}
