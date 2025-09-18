package com.ems.common.vo;

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
public class EmployeeCreatedEvent implements Serializable {

    @Serial
    private static final long serialVersionUID = 7846796307831816615L;

    private Long userId;
    private String employeeId;
}

