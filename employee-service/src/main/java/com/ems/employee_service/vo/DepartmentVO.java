package com.ems.employee_service.vo;

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
public class DepartmentVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 2511595351004305890L;

    private Long id;
    private String name;
    private String description;
    private String location;
    private Long managerId;
    private String managerName;
    private Integer employeeCount;
    private Long createdAt;
    private Long updatedAt;
}
