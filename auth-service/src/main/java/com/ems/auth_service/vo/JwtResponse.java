package com.ems.auth_service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = -3954610652669710833L;

    private String token;
    private String type = "Bearer";
    private String refreshToken;
    private Long id;
    private String username;
    private String email;
    private List<String> roles;


    public JwtResponse(String token, String type, String refreshToken) {
        this.token = token;
        this.type = type;
        this.refreshToken = refreshToken;
    }

    public JwtResponse(String token, String refreshToken) {
        this.token=token;
        this.refreshToken=refreshToken;
    }
}

