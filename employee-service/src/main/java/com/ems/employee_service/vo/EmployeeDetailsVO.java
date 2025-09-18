package com.ems.employee_service.vo;

import com.ems.common.vo.UserProfileVO;
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
public class EmployeeDetailsVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 5491718439165111324L;

    private EmployeeVO employee;
    private UserProfileVO userDetails;
    private DepartmentVO department;
    private PositionVO position;
}
