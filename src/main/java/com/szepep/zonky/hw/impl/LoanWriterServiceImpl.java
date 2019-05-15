package com.szepep.zonky.hw.impl;

import com.szepep.zonky.hw.api.LoanWriterService;
import com.szepep.zonky.hw.dto.Loan;
import org.springframework.stereotype.Service;

@Service
public class LoanWriterServiceImpl implements LoanWriterService<Void> {

    @Override
    public Void writeLoan(Loan loan) {
        System.out.println(loan.getId());
        return null;
    }
}
