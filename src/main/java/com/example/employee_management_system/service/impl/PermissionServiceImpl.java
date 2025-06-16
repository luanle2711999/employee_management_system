package com.example.employee_management_system.service.impl;

import com.example.employee_management_system.dto.request.PermissionCreationDto;
import com.example.employee_management_system.entity.Permission;
import com.example.employee_management_system.exception.BadRequestException;
import com.example.employee_management_system.mapper.PermissionMapper;
import com.example.employee_management_system.repository.PermissionRepository;
import com.example.employee_management_system.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionServiceImpl implements PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;
    @NonFinal
    @Value("${jwt.signerKey}")
    private String SIGNED_KEY;


    public Permission createPermission(PermissionCreationDto permissionCreationDto) {
        Permission newPermission = permissionMapper.toPermission(permissionCreationDto);
        return permissionRepository.save(newPermission);
    }

    public Set<Permission> findPermissionsByNames(Set<String> permissionNames) {
        List<Permission> permissions = permissionRepository.findAllById(permissionNames);
        List<String> foundNames = permissions.stream()
                .map(Permission::getName)
                .toList();
        List<String> missingNames = permissionNames.stream()
                .filter(item -> !foundNames.contains(item))
                .toList();
        if (!missingNames.isEmpty()) {
            throw new BadRequestException("Could not find roles" + missingNames.toString());

        }
        return new HashSet<>(permissions);
    }

    public List<Permission> findAllPermissions() {
        return permissionRepository.findAll();
    }

    public Set<Permission> findMyPermissions(String token) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(SIGNED_KEY.getBytes(), "HS512");
        NimbusJwtDecoder parsedToken = NimbusJwtDecoder.withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
        var decodedToken = parsedToken.decode(token);
        String permissionNames = decodedToken.getClaim("permissions");
        Set<String> listPermissions = new HashSet<>(Arrays.asList(permissionNames.split(",")));
        return findPermissionsByNames(listPermissions);
    }
}
