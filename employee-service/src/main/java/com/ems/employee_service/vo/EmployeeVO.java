package com.ems.employee_service.vo;

import com.ems.employee_service.enums.EmployeeStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -8273613690899630478L;

    private Long id;
    private String employeeId;
    private String phone;
    private Long dateOfBirth;
    private Long hireDate;
    private EmployeeStatus status;
    private Long userId;
    private Long departmentId;
    private Long positionId;
    private String departmentName;
    private String positionTitle;
}
