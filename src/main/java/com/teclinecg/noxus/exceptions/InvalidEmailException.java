package com.teclinecg.noxus.exceptions;

public class InvalidEmailException extends RuntimeException {

    public InvalidEmailException(String msg) {
        super(msg);
    }
}
