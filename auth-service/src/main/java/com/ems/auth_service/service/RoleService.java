package com.ems.auth_service.service;



import com.ems.common.entity.Role;
import com.ems.common.enums.RoleName;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(RoleName roleName);
}
