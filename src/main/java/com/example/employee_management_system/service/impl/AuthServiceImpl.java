package com.example.employee_management_system.service.impl;

import com.example.employee_management_system.common.ApiResponse;
import com.example.employee_management_system.dto.request.LoginRequestDto;
import com.example.employee_management_system.dto.response.LoginResponseDto;
import com.example.employee_management_system.entity.TokenLog;
import com.example.employee_management_system.entity.User;
import com.example.employee_management_system.exception.DeactivatedException;
import com.example.employee_management_system.exception.UnauthenticatedErrorException;
import com.example.employee_management_system.repository.UserRepository;
import com.example.employee_management_system.service.AuthService;
import com.example.employee_management_system.service.TokenLogService;
import com.example.employee_management_system.service.UserService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthServiceImpl implements AuthService {
    UserRepository userRepository;
    BCryptPasswordEncoder passwordEncoder;
    TokenLogService tokenLogService;
    UserService userService;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNED_KEY;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        User existingUser = userRepository.findByUsername(loginRequestDto.getUsername())
                .orElseThrow(() -> new UnauthenticatedErrorException("Unauthenticated!"));
        boolean isMatch = passwordEncoder.matches(loginRequestDto.getPassword(), existingUser.getPassword());
        if (!isMatch) {
            throw new UnauthenticatedErrorException("Unauthenticated!");
        }
        if (!existingUser.getIsActive()) {
            throw new DeactivatedException("User is not active.");
        }
        String token = UUID.randomUUID()
                .toString();
        tokenLogService.createTokenLog(TokenLog.builder()
                .tokenId(token)
                .isActive(true)
                .build());
        String jwtToken = generateToken(existingUser, token);
        return LoginResponseDto.builder()
                .token(jwtToken)
                .build();

    }

    public ApiResponse<Boolean> introspect(String token) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGNED_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        Date experyDate = signedJWT.getJWTClaimsSet()
                .getExpirationTime();
        JWTClaimsSet jwtClaimsSet = signedJWT.getJWTClaimsSet();
        String tokenId = jwtClaimsSet.getClaim("token_id")
                .toString();
        Boolean isValid = tokenLogService.isTokenLogValid(tokenId);
        if (!isValid) {
            throw new UnauthenticatedErrorException("Unauthenticated!");
        }
        boolean verified = signedJWT.verify(verifier);
        return ApiResponse.<Boolean>builder()
                .data(verified && experyDate.after(new Date()))
                .status(200)
                .build();
    }

    public LoginResponseDto refreshToken(String token) throws ParseException {
        JWTClaimsSet jwtClaimsSet = SignedJWT.parse(token)
                .getJWTClaimsSet();
        String tokenInJwt = jwtClaimsSet.getClaim("token_id")
                .toString();
        String userId = jwtClaimsSet.getClaim("user_id")
                .toString();
        User existingUser = userService.findUserById(userId);
        if (existingUser == null) {
            throw new UnauthenticatedErrorException("Unauthenticated!");
        }
        Boolean isValid = tokenLogService.isTokenLogValid(tokenInJwt);
        if (isValid) {
            tokenLogService.updateTokenLog(TokenLog.builder()
                    .tokenId(tokenInJwt)
                    .isActive(false)
                    .build());
        }
        String tokenId = UUID.randomUUID()
                .toString();
        tokenLogService.createTokenLog(TokenLog.builder()
                .tokenId(tokenId)
                .isActive(true)
                .build());
        String jwtToken = generateToken(existingUser, tokenId);
        return LoginResponseDto.builder()
                .token(jwtToken)
                .build();
    }

    private String generateToken(User user, String token) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        Map<String, String> roleAndPermission = buildScope(user);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder().subject(user.getUsername())
                .jwtID(UUID.randomUUID()
                        .toString())
                .issuer("http://localhost:9090")
                .claim("scope", roleAndPermission.get("role"))
                .claim("permissions", roleAndPermission.get("permissions"))
                .claim("token_id", token)
                .claim("user_id", user.getId())
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now()
                        .plus(1, ChronoUnit.HOURS)
                        .toEpochMilli()))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNED_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, String> buildScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(",");
        StringJoiner permissions = new StringJoiner(",");
        if (!CollectionUtils.isEmpty(user.getRoles())) {
            user.getRoles()
                    .forEach(role -> {
                        stringJoiner.add("ROLE_" + role.getName());
                        if (!CollectionUtils.isEmpty(role.getPermissions())) role.getPermissions()
                                .forEach(permission -> permissions.add(permission.getName()));
                    });
        }
        Map<String, String> map = new HashMap<>();
        map.put("role", stringJoiner.toString());
        map.put("permissions", permissions.toString());
        return map;

    }
}
