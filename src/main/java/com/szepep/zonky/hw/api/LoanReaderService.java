package com.szepep.zonky.hw.api;

import com.szepep.zonky.hw.dto.Loan;

import java.time.OffsetDateTime;
import java.util.List;

public interface LoanReaderService {

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
