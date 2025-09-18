package com.ems.employee_service.service;

import com.ems.common.utils.PageResponse;
import com.ems.employee_service.vo.CreateEmployeeRequestVO;
import com.ems.employee_service.vo.EmployeeDetailsVO;
import com.ems.employee_service.vo.EmployeeVO;
import com.ems.employee_service.vo.UpdateEmployeeRequestVO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {

     EmployeeDetailsVO getEmployeeWithUserInfo(String employeeId);

     EmployeeVO createEmployee(@Valid CreateEmployeeRequestVO request);

     PageResponse<?> getAllEmployees(Pageable pageable);

     EmployeeVO updateEmployee(String employeeId, @Valid UpdateEmployeeRequestVO request);
}
