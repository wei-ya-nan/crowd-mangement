package com.wyn.crowd.exception;

/**
 * @author wei-ya-nan
 * @version 1.0
 * @date 2022/3/17
 */
public class LoginAcctAlreadyInUserException extends RuntimeException{
    public LoginAcctAlreadyInUserException() {
    }

    public LoginAcctAlreadyInUserException(String message) {
        super(message);
    }

    public LoginAcctAlreadyInUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginAcctAlreadyInUserException(Throwable cause) {
        super(cause);
    }

    public LoginAcctAlreadyInUserException(String message, Throwable cause, boolean enableSuppression,
                                           boolean writableStackTrace) {

    }
}
