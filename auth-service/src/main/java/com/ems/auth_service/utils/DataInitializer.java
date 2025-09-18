package com.ems.auth_service.utils;


import com.ems.common.entity.Role;
import com.ems.common.enums.RoleName;
import com.ems.common.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        // Initialize default roles
        if (roleRepository.count() == 0) {
            roleRepository.save(new Role(RoleName.ROLE_ADMIN, "Administrator"));
            roleRepository.save(new Role(RoleName.ROLE_HR, "Human Resources"));
            roleRepository.save(new Role(RoleName.ROLE_MANAGER, "Manager"));
            roleRepository.save(new Role(RoleName.ROLE_EMPLOYEE, "Employee"));
        }
    }
}
