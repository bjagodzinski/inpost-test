package com.example.organization.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmpAlreadyExistsException extends ResponseStatusException {
    public EmpAlreadyExistsException() {
        super(HttpStatus.BAD_REQUEST, "Employee already exists");
    }
}
