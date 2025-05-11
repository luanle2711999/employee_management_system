package com.example.employee_management_system.configuration;

import com.example.employee_management_system.dto.request.DepartmentCreationDto;
import com.example.employee_management_system.dto.request.EmployeeCreationDto;
import com.example.employee_management_system.dto.request.UserCreationRequestDto;
import com.example.employee_management_system.entity.*;
import com.example.employee_management_system.enums.PermissionName;
import com.example.employee_management_system.mapper.DepartmentMapper;
import com.example.employee_management_system.mapper.EmployeeMapper;
import com.example.employee_management_system.mapper.UserMapper;
import com.example.employee_management_system.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ApplicationInitConfig {
    private DepartmentMapper departmentMapper;
    private EmployeeMapper employeeMapper;
    private UserMapper userMapper;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository, EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, PermissionRepository permissionRepository, RoleRepository roleRepository) {
        return args -> {
            if (!userRepository.existsByRoleName("ADMIN")) {
                // create permissions
                Arrays.stream(PermissionName.values())
                      .forEach(permissionNameEnum -> {
                          permissionRepository.findByName(permissionNameEnum.name()
                                                                            .toString())
                                              .orElseGet(() -> permissionRepository.save(
                                                      Permission.builder()
                                                                .name(permissionNameEnum.toString())
                                                                .description(permissionNameEnum.getDescription()
                                                                                               .toString())
                                                                .build()
                                              ));
                      });
                log.info("Created permission successfully!");

                //create role
                List<Permission> allPermissions = permissionRepository.findAll();
                Role adminRole = Role.builder()
                                     .name("ADMIN")
                                     .description("All permissions")
                                     .permissions(new HashSet<>(allPermissions))
                                     .build();
                Role newRole = roleRepository.save(adminRole);
                log.info("Created role successfully!");
                //create department
                DepartmentCreationDto departmentCreationDto = DepartmentCreationDto.builder()
                                                                                   .description("Office supplies, scheduling, front-desk, clerical work")
                                                                                   .name("Administration")
                                                                                   .build();

                Department newDepartment = departmentRepository.save(departmentMapper.toDepartment(departmentCreationDto));
                log.info(("Created department successfully!"));
                //create employee
                EmployeeCreationDto employeeCreationDto = EmployeeCreationDto.builder()
                                                                             .address("Hà Nội")
                                                                             .dob(LocalDate.of(1999, Month.JANUARY, 27))
                                                                             .email("lecongluan99@gmail.com")
                                                                             .dateOfJoining(LocalDate.now())
                                                                             .departmentId(newDepartment.getId())
                                                                             .firstName("Luan")
                                                                             .lastName("Le")
                                                                             .jobTitle("CEO")
                                                                             .phoneNumber("098612345")
                                                                             .build();

                Employee newEmployee = employeeMapper.toEmployee(employeeCreationDto);
                newEmployee.setDepartment(newDepartment);
                employeeRepository.save(newEmployee);

                log.info("Created employee successfully!");
                //create user
                UserCreationRequestDto userCreationRequestDto = UserCreationRequestDto.builder()
                                                                                      .employeeId(newEmployee.getId())
                                                                                      .isActive(true)
                                                                                      .password(passwordEncoder().encode("admin"))
                                                                                      .username("admin")
                                                                                      .roles(Set.of(newRole.getName()))
                                                                                      .build();
                User newUser = userMapper.toUser(userCreationRequestDto);
                newUser.setRoles(Set.of(newRole));
                newUser.setEmployee(newEmployee);
                userRepository.save(newUser);
                log.info("Created user successfully!");
            }

        };
    }
}
