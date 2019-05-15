package com.szepep.zonky.hw.impl;

import com.szepep.zonky.hw.api.Job;
import com.szepep.zonky.hw.api.LoanReaderService;
import com.szepep.zonky.hw.api.LoanWriterService;
import com.szepep.zonky.hw.dto.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class LoanJobImpl implements Job {

    private final LoanReaderService reader;
    private final LoanWriterService writer;
    private OffsetDateTime lastTimestamp;

    @Autowired
    LoanJobImpl(LoanReaderService reader,
                LoanWriterService<?> writer,
                @Value("${zonky.first.read.time.value}") int period,
                @Value("${zonky.first.read.time.unit}") ChronoUnit unit) {
        this.reader = reader;
        this.writer = writer;
        lastTimestamp = OffsetDateTime.now().minus(period, unit);
    }

    @Override
    public void run() {
        List<Loan> loans = reader.getLoansFrom(lastTimestamp);
        loans.forEach(writer::writeLoan);
    }
}
