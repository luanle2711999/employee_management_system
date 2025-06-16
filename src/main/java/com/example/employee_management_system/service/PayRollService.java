package com.example.employee_management_system.service;

import com.example.employee_management_system.dto.request.PayRollCreationDto;
import com.example.employee_management_system.entity.PayRoll;

public interface PayRollService {
    PayRoll createPayRoll(PayRollCreationDto payRollCreationDto);
}
