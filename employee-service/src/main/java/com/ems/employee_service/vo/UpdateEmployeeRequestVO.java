package com.ems.employee_service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeRequestVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -7540287428013240441L;

    private String officialEmail;
    private String phone;
    private Long hireDate;
    private Long departmentId;
    private Long positionId;
    private Long dob;
}
