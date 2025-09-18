package com.ems.auth_service.vo;

import jakarta.validation.constraints.NotBlank;
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
public class TokenRefreshRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 6340970095397834494L;

    @NotBlank
    private String refreshToken;
}

