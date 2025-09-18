package com.ems.employee_service.clients;

import com.ems.common.vo.UserProfileVO;
import org.springframework.stereotype.Component;

@Component
public class AuthServiceFallback implements AuthServiceClient {

    @Override
    public UserProfileVO getUserById(Long userId) {
        return UserProfileVO.builder()
                .id(userId)
                .username("unavailable")
                .email("service.unavailable@system.com")
                .build();
    }

    @Override
    public Boolean isUserActive(Long userId) {
        return false;
    }

    @Override
    public String testPoint() {
        return "Test failed";
    }
}
