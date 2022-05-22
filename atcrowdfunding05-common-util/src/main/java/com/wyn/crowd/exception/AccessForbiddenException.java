package com.wyn.crowd.exception;

/**
 * @author wei-ya-nan
 * @version 1.0
 * @date 2022/3/16
 */
public class AccessForbiddenException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public AccessForbiddenException() {
    }

    public AccessForbiddenException(String message) {
        super(message);
    }

    public AccessForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessForbiddenException(Throwable cause) {
        super(cause);
    }

    public AccessForbiddenException(String message, Throwable cause, boolean enableSuppression,
                                    boolean writableStackTrace) {

    }
}
