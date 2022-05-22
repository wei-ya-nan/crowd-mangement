package com.wyn.crowd.exception;

/**
 * @author wei-ya-nan
 * @version 1.0
 * @date 2022/3/17
 */
public class LoginAcctAlreadyInUseForUpdateException extends RuntimeException {
    public LoginAcctAlreadyInUseForUpdateException() {
    }

    public LoginAcctAlreadyInUseForUpdateException(String message) {
        super(message);
    }

    public LoginAcctAlreadyInUseForUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginAcctAlreadyInUseForUpdateException(Throwable cause) {
        super(cause);
    }

    public LoginAcctAlreadyInUseForUpdateException(String message, Throwable cause, boolean enableSuppression,
                                                   boolean writableStackTrace) {
    }
}
