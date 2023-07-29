package com.teclinecg.noxus.exceptions;

public class InvalidCreditCardExpirationDateException extends RuntimeException {

    public InvalidCreditCardExpirationDateException(String msg) {
        super(msg);
    }
}
