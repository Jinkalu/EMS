package com.ems.auth_service.service.impl;


import com.ems.auth_service.service.RoleService;
import com.ems.common.entity.Role;
import com.ems.common.enums.RoleName;
import com.ems.common.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(RoleName roleName) {
        return roleRepository.findByName(roleName);
    }
}
