package com.ems.employee_service.vo;

import com.ems.employee_service.enums.EmployeeStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class CreateEmployeeRequestVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 8534612077253010516L;

    @NotNull
    private Long userId;

    @NotBlank
    @Size(max = 20)
    private String employeeId;

    private String phone;

//    @NotNull
    private Long departmentId;

//    @NotNull
    private Long positionId;

    private Long hireDate;

    private EmployeeStatus status;
}



