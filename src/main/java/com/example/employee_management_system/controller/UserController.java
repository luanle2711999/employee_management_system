package com.example.employee_management_system.controller;

import com.example.employee_management_system.common.ApiResponse;
import com.example.employee_management_system.dto.request.DeactivateUserDto;
import com.example.employee_management_system.dto.request.UserCreationRequestDto;
import com.example.employee_management_system.dto.response.UserResponseDto;
import com.example.employee_management_system.entity.User;
import com.example.employee_management_system.mapper.UserMapper;
import com.example.employee_management_system.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserController {
    UserServiceImpl userServiceImpl;
    UserMapper userMapper;

    @PostMapping
    public ApiResponse<UserResponseDto> createUser(@RequestBody @Valid UserCreationRequestDto userCreationRequestDto) {
        User user = userServiceImpl.createUser(userCreationRequestDto);
        UserResponseDto userResponseDto = userMapper.toUserResponse(user);
        return ApiResponse.<UserResponseDto>builder()
                          .data(userResponseDto)
                          .status(200)
                          .message("User created successfully!")
                          .build();
    }

    @PostMapping("/deactivate")
    public ApiResponse<Boolean> deactivateUser(@RequestBody @Valid DeactivateUserDto deactivateUserDto) {
        userServiceImpl.deactivateUser(deactivateUserDto.getUsername());
        return ApiResponse.<Boolean>builder().message("Deactivated successfully").status(200).build();
    }

    @GetMapping
    public ApiResponse<List<UserResponseDto>> getAllUsers() {
        var authentication = SecurityContextHolder.getContext()
                                                  .getAuthentication();
        log.info("Username" + authentication.getName());
        if (authentication.getPrincipal() instanceof Jwt jwt) {
            String permissionsStr = jwt.getClaimAsString("permissions");
            log.info(permissionsStr);
        }
        authentication.getAuthorities()
                      .stream()
                      .forEach(item -> log.info(item.getAuthority()));
        List<User> allUsers = userServiceImpl.getAllUsers();
        List<UserResponseDto> allUserReponse = allUsers.stream()
                                                       .map(userMapper::toUserResponse)
                                                       .toList();
        return ApiResponse.<List<UserResponseDto>>builder()
                          .data(allUserReponse)
                          .status(200)
                          .build();
    }
}
