package com.ems.common.vo;

import com.ems.common.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserProfileVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -4432562214837002635L;

    private Long id;
    private String employeeId;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private UserStatus status;
    private Set<String> roles;
    private LocalDateTime createdAt;
}

