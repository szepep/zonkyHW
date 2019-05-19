package com.szepep.zonky.hw.impl;

import com.szepep.zonky.hw.api.Job;
import com.szepep.zonky.hw.api.LoanException;
import com.szepep.zonky.hw.api.LoanReaderService;
import com.szepep.zonky.hw.api.LoanWriterService;
import com.szepep.zonky.hw.dto.Loan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
class LoanJobImpl implements Job {

    private static final Logger log = LoggerFactory.getLogger(LoanJobImpl.class);

    private final LoanReaderService reader;
    private final LoanWriterService writer;
    private OffsetDateTime lastTimestamp;
    private volatile Set<Integer> alreadyRetrievedIDs = Collections.emptySet();

    @Autowired
    LoanJobImpl(LoanReaderService reader,
                LoanWriterService writer,
                @Value("${zonky.first.read.time.value}") int readBack,
                @Value("${zonky.first.read.time.unit}") ChronoUnit unit) {
        this.reader = reader;
        this.writer = writer;
        lastTimestamp = OffsetDateTime.now().minus(readBack, unit);
    }

    @Override
    public void run() {
        try {
            List<Loan> loans = reader.getLoansFrom(lastTimestamp);
            if (loans.isEmpty()) {
                return;
            }
            Loan lastItem = loans.get(loans.size() - 1);
            String datePublished = lastItem.getDatePublished();

            List<Loan> newLoans = loans.stream()
                    .filter(l -> !alreadyRetrievedIDs.contains(l.getId()))
                    .collect(Collectors.toList());

            // To ensure visibility the elements of the set in multithreaded environment
            // the usage of tmp set is mandatory. For details see
            // https://www.infoq.com/articles/memory_barriers_jvm_concurrency
            @SuppressWarnings("UnnecessaryLocalVariable")
            Set<Integer> tmpAlreadyRetrieved = newLoans.stream()
                    .filter(l -> l.getDatePublished().equals(datePublished))
                    .map(Loan::getId)
                    .collect(Collectors.toSet());
            this.alreadyRetrievedIDs = tmpAlreadyRetrieved;

            for (Loan newLoan : newLoans) {
                writer.writeLoan(newLoan);
            }
            lastTimestamp = OffsetDateTime.parse(datePublished);
        } catch (LoanException e) {
            log.error(e.getMessage(), e);
        }
    }
}
