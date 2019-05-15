package com.szepep.zonky.hw.api;

import com.szepep.zonky.hw.dto.Loan;

import java.time.OffsetDateTime;
import java.util.List;

public interface LoanReaderService {

    List<Loan> getLoansFrom(OffsetDateTime from);
}
