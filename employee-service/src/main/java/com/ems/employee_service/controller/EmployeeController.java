package com.ems.employee_service.controller;

import com.ems.common.utils.ApiResponse;
import com.ems.common.utils.PageResponse;
import com.ems.employee_service.service.EmployeeService;
import com.ems.employee_service.vo.CreateEmployeeRequestVO;
import com.ems.employee_service.vo.EmployeeDetailsVO;
import com.ems.employee_service.vo.EmployeeVO;
import com.ems.employee_service.vo.UpdateEmployeeRequestVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
@PreAuthorize("hasRole('HR') or hasRole('EMPLOYEE')")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    @PreAuthorize("hasRole('HR')")
    public ResponseEntity<ApiResponse<PageResponse<?>>> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        PageResponse<?> employees = employeeService.getAllEmployees(pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, "Employees retrieved", employees));
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<ApiResponse<EmployeeDetailsVO>> getEmployeeById(@PathVariable String employeeId) {
        EmployeeDetailsVO employee = employeeService.getEmployeeWithUserInfo(employeeId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Employee found", employee));
    }

    @PostMapping
    @PreAuthorize("hasRole('HR')")
    public ResponseEntity<ApiResponse<EmployeeVO>> createEmployee(@Valid @RequestBody CreateEmployeeRequestVO request) {
        EmployeeVO employee = employeeService.createEmployee(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Employee created", employee));
    }

    @PutMapping("/{employeeId}")
    @PreAuthorize("hasRole('HR') or (hasRole('EMPLOYEE') and #employeeId == authentication.principal.employeeId)")
    public ResponseEntity<ApiResponse<EmployeeVO>> updateEmployee(@PathVariable String employeeId,
                                                                   @Valid @RequestBody UpdateEmployeeRequestVO request) {
        EmployeeVO employee = employeeService.updateEmployee(employeeId, request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Employee updated", employee));
    }

/*
    @GetMapping("/department/{departmentId}")
    @PreAuthorize("hasRole('HR')")
    public ResponseEntity<ApiResponse<List<EmployeeDTO>>> getEmployeesByDepartment(
            @PathVariable Long departmentId) {
        List<EmployeeDTO> employees = employeeService.getEmployeesByDepartment(departmentId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Employees retrieved", employees));
    }
    */

/*
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<EmployeeDTO>>> searchEmployees(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Long departmentId) {

        EmployeeSearchCriteria criteria = EmployeeSearchCriteria.builder()
                .name(name)
                .email(email)
                .departmentId(departmentId)
                .build();

        List<EmployeeDTO> employees = employeeService.searchEmployees(criteria);
        return ResponseEntity.ok(new ApiResponse<>(true, "Search completed", employees));
    }*/
}
