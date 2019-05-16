package com.szepep.zonky.hw.api;

import com.szepep.zonky.hw.dto.Loan;

@FunctionalInterface
public interface LoanWriterService<R> {

    R writeLoan(Loan loan) throws LoanWriterException;

    final class LoanWriterException extends LoanException {
        public LoanWriterException() {
        }

        public LoanWriterException(String message) {
            super(message);
        }

        public LoanWriterException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
