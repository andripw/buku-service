package com.test.pendaftaranbuku.exception;

public class BadRequestException extends RuntimeException{
    private static final long serialVersionUID = -2613904922805397754L;

    public BadRequestException(String message) {
        super(message);
    }
}
