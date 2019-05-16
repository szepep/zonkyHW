package com.szepep.zonky.hw.api;

/**
 * Base class of exceptions thrown by {@link LoanReaderService} and {@link LoanWriterService}.
 */
@SuppressWarnings("WeakerAccess")
public abstract class LoanException extends Exception {
    public LoanException() {
    }

    public LoanException(String message) {
        super(message);
    }

    public LoanException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoanException(Throwable cause) {
        super(cause);
    }

    public LoanException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
