package com.ems.auth_service.service;

import com.ems.auth_service.vo.JwtResponse;
import com.ems.auth_service.vo.LoginRequest;
import com.ems.auth_service.vo.RegisterRequest;
import com.ems.auth_service.vo.UserRegistrationResponse;
import jakarta.validation.Valid;

public interface AuthService {

    UserRegistrationResponse getUserRegistrationResponse(@Valid RegisterRequest registerRequest);

    JwtResponse userLogin(@Valid LoginRequest loginRequest);

    JwtResponse refreshToken(String requestRefreshToken);
}
