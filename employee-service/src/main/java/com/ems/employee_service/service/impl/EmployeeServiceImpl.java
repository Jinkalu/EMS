package com.ems.employee_service.service.impl;

import com.ems.common.utils.PageResponse;
import com.ems.common.vo.ResourceNotFoundException;
import com.ems.employee_service.clients.AuthServiceClient;
import com.ems.employee_service.entity.Department;
import com.ems.employee_service.entity.Employee;
import com.ems.employee_service.entity.Position;
import com.ems.employee_service.repository.DepartmentRepository;
import com.ems.employee_service.repository.EmployeeRepository;
import com.ems.employee_service.repository.PositionRepository;
import com.ems.employee_service.service.topic.EmployeeEventPublisher;
import com.ems.employee_service.service.EmployeeService;
import com.ems.employee_service.vo.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final AuthServiceClient authServiceClient;
    private final PositionRepository positionRepository;
    private final EmployeeEventPublisher employeeEventPublisher;

    @Override
    public EmployeeDetailsVO getEmployeeWithUserInfo(String employeeId) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
        return getEmployeeDetailsVO(employee);
    }

    @Override
    @Transactional(rollbackOn = {Exception.class})
    public EmployeeVO createEmployee(CreateEmployeeRequestVO request) {
        EmployeeVO employeeVO = Optional.of(employeeRepository.save(Employee.builder()
                .userId(request.getUserId())
                .phone(request.getPhone())
                .status(request.getStatus())
                .department(departmentRepository.findById(request.getDepartmentId())
                        .orElse(null))
                .position(positionRepository.findById(request.getPositionId())
                        .orElse(null))
                .build())).map(EmployeeServiceImpl::employeeVO).orElse(null);
        employeeEventPublisher.publishEmployeeCreatedEvent(request.getUserId(), employeeVO.getEmployeeId());
        return employeeVO;
    }


    @Override
    public PageResponse<?> getAllEmployees(Pageable pageable) {
        Page<Employee> employeePage = employeeRepository.findAll(pageable);
        PageResponse<Object> response = new PageResponse<>(employeePage.getNumber(), employeePage.getSize(),
                employeePage.getTotalPages(), employeePage.getTotalPages(), employeePage.isLast());
        if (!employeePage.isEmpty()) {
            List<Object> list = employeePage.stream()
                    .map(employee -> (Object) getEmployeeDetailsVO(employee)).toList();
            response.setContent(list);
        }
        return response;
    }

    @Override
    public EmployeeVO updateEmployee(String employeeId, UpdateEmployeeRequestVO request) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "employeeId", employeeId));

        employee.setDateOfBirth(request.getDob());
        employee.setPhone(request.getPhone());
        employee.setOfficialEmail(request.getOfficialEmail());
        employee.setDepartment(departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", request.getDepartmentId())));
        employee.setPosition(positionRepository.findById(request.getPositionId())
                .orElseThrow(() -> new ResourceNotFoundException("Position", "id", request.getPositionId())));
        employee.setHireDate(request.getHireDate());

        Employee saved = employeeRepository.save(employee);
        return employeeVO(saved);
    }


    private PositionVO convertToPositionDTO(Position position) {
        return PositionVO.builder()
                .title(position.getTitle())
                .description(position.getDescription())
                .build();
    }

    private DepartmentVO convertToDepartmentDTO(Department department) {
        return DepartmentVO.builder()
                .name(department.getName())
                .description(department.getDescription())
                .build();
    }


    private EmployeeDetailsVO getEmployeeDetailsVO(Employee employee) {
        return EmployeeDetailsVO.builder()
                .employee(employeeVO(employee))
                .userDetails(authServiceClient.getUserById(employee.getUserId()))
                .department(convertToDepartmentDTO(employee.getDepartment()))
                .position(convertToPositionDTO(employee.getPosition()))
                .build();
    }

    private static EmployeeVO employeeVO(Employee employee) {
        return EmployeeVO.builder()
                .employeeId(employee.getEmployeeId())
                .departmentId(employee.getDepartment().getId())
                .departmentName(employee.getDepartment().getName())
                .positionId(employee.getPosition().getId())
                .positionTitle(employee.getPosition().getDescription())
                .build();
    }
}
