package com.example.organization.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.function.Supplier;

public class NotFoundException extends ResponseStatusException {
    public NotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, "Object not found with id: " + id);
    }

    public static Supplier<NotFoundException> getExceptionSupplier(Long id) {
        return () -> new NotFoundException(id);
    }

}
