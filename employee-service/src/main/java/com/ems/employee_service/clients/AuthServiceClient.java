package com.ems.employee_service.clients;

import com.ems.common.vo.UserProfileVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "auth-service",
        url = "http://localhost:8080/auth-service",
        fallback = AuthServiceFallback.class
)
public interface AuthServiceClient {
    @GetMapping("/api/users/{userId}")
    UserProfileVO getUserById(@PathVariable("userId") Long userId);

    @GetMapping("/api/users/{userId}/active")
    Boolean isUserActive(@PathVariable("userId") Long userId);

    @GetMapping("/api/test/test")
    String testPoint();
}

