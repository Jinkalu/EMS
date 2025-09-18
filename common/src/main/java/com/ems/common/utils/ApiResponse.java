package com.ems.common.utils;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class ApiResponse<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 6399690991840968983L;

    private Boolean success;
    private String message;
    private T data;

    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
