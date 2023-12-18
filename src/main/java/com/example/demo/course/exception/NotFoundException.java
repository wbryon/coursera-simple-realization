package com.example.demo.course.exception;

import java.time.OffsetDateTime;

public class NotFoundException extends RuntimeException {
    private OffsetDateTime dateOccurred;
    public NotFoundException(OffsetDateTime dateOccurred, String message) {
        super(message);
    }
}
