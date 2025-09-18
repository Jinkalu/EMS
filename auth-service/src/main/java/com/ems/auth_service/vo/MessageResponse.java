package com.ems.auth_service.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 6946402329018077096L;

    private String message;
}

