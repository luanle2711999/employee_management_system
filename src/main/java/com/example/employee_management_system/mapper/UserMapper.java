package com.example.employee_management_system.mapper;

import com.example.employee_management_system.dto.request.UserCreationRequestDto;
import com.example.employee_management_system.dto.response.UserResponseDto;
import com.example.employee_management_system.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "isActive", source = "isActive", defaultValue = "false")
    User toUser(UserCreationRequestDto userCreationRequestDto);

    UserResponseDto toUserResponse(User user);
}
