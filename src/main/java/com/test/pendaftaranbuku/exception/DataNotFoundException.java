package com.test.pendaftaranbuku.exception;

public class DataNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 6309182454536424747L;

    public DataNotFoundException(String message) {
        super(message);
    }
}
