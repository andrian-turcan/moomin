package com.mommin.system.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public final class ValidationException extends RuntimeException {
    
    public ValidationException(String message) {
        super(message);
    }
}
