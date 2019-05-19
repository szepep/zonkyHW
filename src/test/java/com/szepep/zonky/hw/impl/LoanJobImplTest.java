package com.szepep.zonky.hw.impl;

import com.szepep.zonky.hw.api.LoanReaderService;
import com.szepep.zonky.hw.api.LoanWriterService;
import com.szepep.zonky.hw.dto.Loan;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class LoanJobImplTest {

    @Test
    public void testFirstRead() throws LoanReaderService.LoanReaderException {
        LoanReaderService reader = mock(LoanReaderService.class);
        LoanWriterService writer = mock(LoanWriterService.class);

        long seed = System.currentTimeMillis();
        Random r = new Random(seed);
        int readBack = 1 + r.nextInt(10);
        ChronoUnit unit = ChronoUnit.DAYS;
        LoanJobImpl underTest = new LoanJobImpl(reader, writer, readBack, unit);

        final OffsetDateTime[] expectedTime = new OffsetDateTime[1];
        final OffsetDateTime[] actualTime = new OffsetDateTime[1];
        doAnswer(invocationOnMock -> {
            actualTime[0] = invocationOnMock.getArgument(0);
            expectedTime[0] = OffsetDateTime.now().minus(readBack, unit);
            return Collections.emptyList();
        }).when(reader).getLoansFrom(any(OffsetDateTime.class));

        underTest.run();

        long expected = getEpoch(expectedTime[0]);
        long actual = getEpoch(actualTime[0]);
        assertTrue(String.format("First read not called with the defined read-back. Seed = %d, expected%d, actual: %d",
                seed, expected, actual),
                expected - actual < 100);
    }

    @Test
    public void testNoResult() throws LoanReaderService.LoanReaderException {
        LoanReaderService reader = mock(LoanReaderService.class);
        LoanWriterService writer = mock(LoanWriterService.class);

        LoanJobImpl underTest = new LoanJobImpl(reader, writer, 1, ChronoUnit.DAYS);
        when(reader.getLoansFrom(any(OffsetDateTime.class))).thenReturn(Collections.emptyList());

        underTest.run();
        verifyZeroInteractions(writer);
    }

    @Test
    public void testWrite() throws LoanReaderService.LoanReaderException, LoanWriterService.LoanWriterException {
        LoanReaderService reader = mock(LoanReaderService.class);
        LoanWriterService writer = mock(LoanWriterService.class);

        List<Loan> loans = Arrays.asList(createLoan(1), createLoan(2), createLoan(3));

        LoanJobImpl underTest = new LoanJobImpl(reader, writer, 1, ChronoUnit.DAYS);
        when(reader.getLoansFrom(any(OffsetDateTime.class))).thenReturn(loans);

        underTest.run();
        ArgumentCaptor<Loan> loanCaptor = ArgumentCaptor.forClass(Loan.class);
        verify(writer, times(loans.size())).writeLoan(loanCaptor.capture());
        assertEquals(loans, loanCaptor.getAllValues());
    }

    @Test
    public void testSecondReadTimestamp() throws LoanReaderService.LoanReaderException, LoanWriterService.LoanWriterException {
        LoanReaderService reader = mock(LoanReaderService.class);
        LoanWriterService writer = mock(LoanWriterService.class);

        Loan loan1 = createLoan(1);
        Loan loan2 = createLoan(2, OffsetDateTime.parse(loan1.getDatePublished()).plusMinutes(2).toString());
        Loan loan3 = createLoan(3, OffsetDateTime.parse(loan1.getDatePublished()).plusMinutes(5).toString());

        LoanJobImpl underTest = new LoanJobImpl(reader, writer, 1, ChronoUnit.DAYS);
        when(reader.getLoansFrom(any(OffsetDateTime.class)))
                .thenReturn(Arrays.asList(loan1, loan2))
                .thenReturn(Arrays.asList(loan2, loan3));

        underTest.run();
        underTest.run();

        ArgumentCaptor<OffsetDateTime> timestampCaptor = ArgumentCaptor.forClass(OffsetDateTime.class);
        verify(reader, times(2)).getLoansFrom(timestampCaptor.capture());
        assertEquals(loan2.getDatePublished(), timestampCaptor.getValue().toString());

        ArgumentCaptor<Loan> loanCaptor = ArgumentCaptor.forClass(Loan.class);
        verify(writer, times(3)).writeLoan(loanCaptor.capture());
        assertEquals(Arrays.asList(loan1, loan2, loan3), loanCaptor.getAllValues());
    }

    private Loan createLoan(int id) {
        Loan loan = new Loan();
        loan.setId(id);
        loan.setDatePublished(OffsetDateTime.now().toString());
        return loan;
    }

    private Loan createLoan(int id, String published) {
        Loan loan = new Loan();
        loan.setId(id);
        loan.setDatePublished(published);
        return loan;
    }

    private long getEpoch(OffsetDateTime offsetDateTime) {
        return offsetDateTime.toInstant().toEpochMilli();
    }
}