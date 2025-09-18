package com.ems.common.vo;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;
import java.io.Serializable;

@Getter
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException implements Serializable {

    @Serial
    private static final long serialVersionUID = -1645821463089567421L;

    private String fieldName;
    private Object fieldValue;

    public BadRequestException( String fieldName, Object fieldValue) {
        super(String.format("Bad request on %s : '%s'", fieldName, fieldValue));
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public BadRequestException(String message) {
        super(message);
    }

}
