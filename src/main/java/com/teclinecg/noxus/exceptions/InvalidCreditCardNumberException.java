package com.teclinecg.noxus.exceptions;

public class InvalidCreditCardNumberException extends RuntimeException{

    public InvalidCreditCardNumberException(String msg) {
        super(msg);
    }
}
