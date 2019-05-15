package com.szepep.zonky.hw.api;

import com.szepep.zonky.hw.dto.Loan;

@FunctionalInterface
public interface LoanWriterService<R> {

    R writeLoan(Loan loan);
}
