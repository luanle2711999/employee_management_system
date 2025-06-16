package com.example.employee_management_system.service.impl;

import com.example.employee_management_system.dto.request.UserCreationRequestDto;
import com.example.employee_management_system.entity.Employee;
import com.example.employee_management_system.entity.Role;
import com.example.employee_management_system.entity.User;
import com.example.employee_management_system.exception.BadRequestException;
import com.example.employee_management_system.mapper.UserMapper;
import com.example.employee_management_system.repository.EmployeeRepository;
import com.example.employee_management_system.repository.UserRepository;
import com.example.employee_management_system.service.RoleService;
import com.example.employee_management_system.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    EmployeeRepository employeeRepository;
    RoleService roleService;
    UserMapper userMapper;

    BCryptPasswordEncoder passwordEncoder;


    @PreAuthorize("hasRole('ADMIN')")
    public User createUser(UserCreationRequestDto userCreationRequestDto) {
        if (userRepository.existsByUsername(userCreationRequestDto.getUsername())) {
            throw new BadRequestException("This username is existing already!");
        }
        Employee employee = employeeRepository.findById(userCreationRequestDto.getEmployeeId())
                .orElseThrow(() -> new BadRequestException("Employee not found!"));
        Set<Role> roles = roleService.findRolesByNames(userCreationRequestDto.getRoles());
        User newUser = userMapper.toUser(userCreationRequestDto);
        String hashedPassword = passwordEncoder.encode(userCreationRequestDto.getPassword());
        newUser.setPassword(hashedPassword);
        newUser.setEmployee(employee);
        newUser.setRoles(roles);
        return userRepository.save(newUser);
    }

    @PreAuthorize("hasAuthority('USER_DEACTIVATE')")
    public boolean deactivateUser(String username) {
        try {
            User existingUser = userRepository.findByUsername(username)
                    .orElseThrow(() -> new BadRequestException("User does not exist"));
            existingUser.setIsActive(false);
            userRepository.save(existingUser);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public User findUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException("User does not exist"));
    }

    public User deleteUserById(String userId) {
        User user = findUserById(userId);
        userRepository.delete(user);
        return user;
    }

    @PreAuthorize("hasAuthority('EMPLOYEE_VIEW')")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
