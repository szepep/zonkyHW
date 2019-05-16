package com.szepep.zonky.hw.api;

import com.szepep.zonky.hw.dto.Loan;

/**
 * Writer for loans.
 */
@FunctionalInterface
public interface LoanWriterService {

    /**
     * Writes loan.
     *
     * @param loan The loan to write.
     * @throws LoanWriterException When any problem occurres while writing loan.
     */
    void writeLoan(Loan loan) throws LoanWriterException;

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
