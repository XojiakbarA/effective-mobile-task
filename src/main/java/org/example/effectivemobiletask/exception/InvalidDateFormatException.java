package org.example.effectivemobiletask.exception;

public class InvalidDateFormatException extends RuntimeException {
    public InvalidDateFormatException(String field) {
        super(String.format("%s format must be yyyy-MM-dd", field));
    }
}
