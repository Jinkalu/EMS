package com.ems.employee_service.utils;


import com.ems.common.entity.Role;
import com.ems.common.enums.RoleName;
import com.ems.common.repository.RoleRepository;
import com.ems.employee_service.entity.Department;
import com.ems.employee_service.entity.Position;
import com.ems.employee_service.repository.DepartmentRepository;
import com.ems.employee_service.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;

    @Override
    public void run(String... args) {

        if (departmentRepository.count() == 0) {
            departmentRepository.saveAll(List.of(
                    Department.builder()
                            .name("IT")
                            .description("IT Support")
                            .build(),
                    Department.builder()
                            .name("Development")
                            .description("Software development")
                            .build()
            ));
        }
        if (positionRepository.count() == 0) {
            positionRepository.saveAll(List.of(
                    Position.builder()
                            .title("Lead")
                            .description("IT department lead")
                            .department(departmentRepository.findByName("IT"))
                            .build(),
                    Position.builder()
                            .title("Java Lead")
                            .description("Java development lead")
                            .department(departmentRepository.findByName("Development"))
                            .build()
            ));
        }
    }
}

