package com.example.employee_management_system.service.impl;

import com.example.employee_management_system.dto.request.PayRollCreationDto;
import com.example.employee_management_system.entity.Employee;
import com.example.employee_management_system.entity.PayRoll;
import com.example.employee_management_system.mapper.PayRollMapper;
import com.example.employee_management_system.repository.PayRollRepository;
import com.example.employee_management_system.service.PayRollService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PayRollServiceImpl implements PayRollService {
    PayRollRepository payRollRepository;
    EmployeeServiceImpl employeeServiceImpl;
    PayRollMapper payRollMapper;

    public PayRoll createPayRoll(PayRollCreationDto payRollCreationDto) {
        Employee employee = employeeServiceImpl.findEmployeeById(payRollCreationDto.getEmployeeId());
        PayRoll payRoll = payRollMapper.toPayRoll(payRollCreationDto);
        payRoll.setEmployee(employee);
        payRollRepository.save(payRoll);
        return payRoll;
    }
}
