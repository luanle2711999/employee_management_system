package com.example.employee_management_system.mapper;

import com.example.employee_management_system.dto.request.PayRollCreationDto;
import com.example.employee_management_system.entity.PayRoll;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PayRollMapper {
    PayRoll toPayRoll(PayRollCreationDto payRollCreationDto);
}
