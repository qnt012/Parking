package com.nhnacademy.parking.exception;

public class ShortOfMoneyException extends RuntimeException{
    public ShortOfMoneyException(String message) {
        super("Short of Money. "+message);
    }
}
