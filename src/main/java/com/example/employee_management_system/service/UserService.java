package com.example.employee_management_system.service;

import com.example.employee_management_system.dto.request.UserCreationRequestDto;
import com.example.employee_management_system.entity.User;

import java.util.List;

public interface UserService {
    User createUser(UserCreationRequestDto userCreationRequestDto);

    boolean deactivateUser(String username);

    User findUserById(String userId);

    User deleteUserById(String userId);

    List<User> getAllUsers();
}
