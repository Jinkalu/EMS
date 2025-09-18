package com.ems.auth_service.service;

import com.ems.auth_service.entity.RefreshToken;
import jakarta.transaction.Transactional;

import java.util.Optional;

public interface RefreshTokenService {

    RefreshToken createRefreshToken(Long userId);

    RefreshToken verifyExpiration(RefreshToken token) ;

    Optional<RefreshToken> findByToken(String token) ;


    int deleteByUserId(Long userId);

    @Transactional
    void deleteByToken(String token);

    boolean isTokenExpired(RefreshToken token);
}
