package com.example.simplerestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class StudentAlreadyExistsException extends Exception{

    private static final long serialVersionUID = 1L;

    public StudentAlreadyExistsException(String message) {
        super(message);
    }
}
