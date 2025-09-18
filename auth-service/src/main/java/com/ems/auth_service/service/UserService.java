package com.ems.auth_service.service;


import com.ems.auth_service.vo.RegisterRequest;
import com.ems.common.entity.User;
import com.ems.common.vo.UserProfileVO;


import java.util.Optional;

public interface UserService {

    User createUser(RegisterRequest registerRequest);

    Optional<User> findByUsername(String username);

    UserProfileVO getUserProfile(String username) ;

    boolean existsByUsername(String username) ;

    boolean existsByEmail(String email);

    User saveUser(User user);

    UserProfileVO getUser(Long userId);
}
