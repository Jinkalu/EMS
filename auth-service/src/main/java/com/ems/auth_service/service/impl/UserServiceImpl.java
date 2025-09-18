package com.ems.auth_service.service.impl;


import com.ems.auth_service.service.UserService;
import com.ems.auth_service.vo.RegisterRequest;
import com.ems.common.entity.User;
import com.ems.common.repository.UserRepository;
import com.ems.common.vo.UserProfileVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    public User createUser(RegisterRequest registerRequest) {
        // Implementation
        return null;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UserProfileVO getUserProfile(String username) {
        // Implementation
        return null;
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserProfileVO getUser(Long userId) {
        return userRepository.findById(userId)
                .map(user -> UserProfileVO.builder()
                        .id(userId)
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .status(user.getStatus())
                        .build()
                ).orElse(null);
    }
}
