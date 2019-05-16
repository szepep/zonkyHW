package com.szepep.zonky.hw.api;

import com.szepep.zonky.hw.dto.Loan;

import java.time.OffsetDateTime;
import java.util.List;

public interface LoanReaderService {

    /**
     * Reads loan where publish date is greater or equal to {@code from}.
     *
     * @param from The timestamp
     * @return Loans where publish date is greater or equal to {@code from} ordered by publish date.
     * @throws LoanReaderException when problem occures while retrieving loans.
     */
    List<Loan> getLoansFrom(OffsetDateTime from) throws LoanReaderException;

    final class LoanReaderException extends LoanException {
        public LoanReaderException() {
        }

        public LoanReaderException(String message) {
            super(message);
        }

        public LoanReaderException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
