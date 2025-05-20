package com.example.employee_management_system.service;

import com.example.employee_management_system.dto.request.UserCreationRequestDto;
import com.example.employee_management_system.entity.Employee;
import com.example.employee_management_system.entity.Role;
import com.example.employee_management_system.entity.User;
import com.example.employee_management_system.exception.BadRequestException;
import com.example.employee_management_system.mapper.UserMapper;
import com.example.employee_management_system.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    EmployeeService employeeService;
    RoleService roleService;
    UserMapper userMapper;

    BCryptPasswordEncoder passwordEncoder;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNED_KEY;

    @PreAuthorize("hasRole('ADMIN')")
    public User createUser(UserCreationRequestDto userCreationRequestDto) {
        if (userRepository.existsByUsername(userCreationRequestDto.getUsername())) {
            throw new BadRequestException("This username is existing already!");
        }
        Employee employee = employeeService.findEmployeeById(userCreationRequestDto.getEmployeeId());
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
            User existingUser = userRepository.findByUsername(username).orElseThrow(() -> new BadRequestException("User does not exist"));
            existingUser.setIsActive(false);
            userRepository.save(existingUser);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @PreAuthorize("hasAuthority('EMPLOYEE_VIEW')")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
