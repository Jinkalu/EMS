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
public class PositionVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -8856710397756161765L;

    private Long id;
    private String title;
    private String description;
    private Long departmentId;
    private String departmentName;
    private Double minSalary;
    private Double maxSalary;
    private String level;
    private Long createdAt;
    private Long updatedAt;
}
