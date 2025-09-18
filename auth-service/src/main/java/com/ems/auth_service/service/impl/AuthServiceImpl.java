package com.ems.auth_service.service.impl;

import com.ems.auth_service.entity.RefreshToken;
import com.ems.auth_service.exception.TokenRefreshException;
import com.ems.auth_service.service.AuthService;
import com.ems.auth_service.service.RefreshTokenService;
import com.ems.auth_service.service.RoleService;
import com.ems.auth_service.service.UserService;
import com.ems.auth_service.vo.JwtResponse;
import com.ems.auth_service.vo.LoginRequest;
import com.ems.auth_service.vo.RegisterRequest;
import com.ems.auth_service.vo.UserRegistrationResponse;
import com.ems.common.auth.JwtUtil;
import com.ems.common.entity.Role;
import com.ems.common.entity.User;
import com.ems.common.enums.RoleName;
import com.ems.common.enums.UserStatus;
import com.ems.common.vo.BadRequestException;
import com.ems.common.vo.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final RoleService roleService;

    private final RefreshTokenService refreshTokenService;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;


    @Override
    public UserRegistrationResponse getUserRegistrationResponse(RegisterRequest registerRequest) {

        registrationValidation(registerRequest);

        // 3. Create new user account
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setStatus(UserStatus.ACTIVE);

        // 4. Assign roles to use
        Set<Role> roles = new HashSet<>();
        Set<String> strRoles = registerRequest.getRoles();

        if (strRoles == null || strRoles.isEmpty()) {
            Role userRole = roleService.findByName(RoleName.ROLE_EMPLOYEE)
                    .orElseThrow(() -> new ResourceNotFoundException("Role", "name", RoleName.ROLE_EMPLOYEE));
            roles.add(userRole);
        } else {
            setRole(strRoles, roles);
        }

        user.setRoles(roles);

        // 5. Save user to database
        User savedUser = userService.saveUser(user);

        // 6. Return success response
        return new UserRegistrationResponse(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getRoles().stream()
                        .map(role -> role.getName().name())
                        .collect(Collectors.toSet()));
    }


    private void registrationValidation(RegisterRequest registerRequest) {
        // 1. Check if username is already taken
        if (userService.existsByUsername(registerRequest.getUsername())) {
            throw new BadRequestException("username", registerRequest.getUsername());
        }

        // 2. Check if email is already in use
        if (userService.existsByEmail(registerRequest.getEmail())) {
            throw new BadRequestException("email", registerRequest.getEmail());
        }
    }


    private void setRole(Set<String> strRoles, Set<Role> roles) {
        Map<String, RoleName> roleMap = Map.of(
                "admin", RoleName.ROLE_ADMIN,
                "hr", RoleName.ROLE_HR,
                "manager", RoleName.ROLE_MANAGER,
                "employee", RoleName.ROLE_EMPLOYEE
        );

        strRoles.forEach(roleStr -> {
            RoleName roleName = roleMap.getOrDefault(roleStr.toLowerCase(), RoleName.ROLE_EMPLOYEE);
            Role role = roleService.findByName(roleName)
                    .orElseThrow(() -> new ResourceNotFoundException("Role", "name", roleStr));
            roles.add(role);
        });
    }

    @Override
    public JwtResponse userLogin(LoginRequest loginRequest) {
        // 1. Authenticate user credentials
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        // 2. Set authentication in security context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 3. Get user details from authentication
        User userPrincipal = (User) authentication.getPrincipal();

        // 4. Generate JWT access token
        String jwt = jwtUtil.generateJwtToken(userPrincipal);

        // 5. Create refresh token
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userPrincipal.getId());

        // 6. Create and return response
        return new JwtResponse(
                jwt,
                "Bearer",
                refreshToken.getToken()
        );
    }

    @Override
    public JwtResponse refreshToken(String requestRefreshToken) {
        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtil.generateTokenFromUsername(user);
                    return new JwtResponse(token, requestRefreshToken);
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }

}
