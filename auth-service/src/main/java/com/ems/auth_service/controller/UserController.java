package com.ems.auth_service.controller;

import com.ems.auth_service.service.UserService;
import com.ems.common.vo.UserProfileVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public UserProfileVO getUser(@PathVariable Long userId){
        return userService.getUser(userId);
    }
}
