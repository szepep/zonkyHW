package com.szepep.zonky.hw.impl;

import com.szepep.zonky.hw.api.LoanReaderService;
import com.szepep.zonky.hw.dto.Loan;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Random;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class LoanReaderServiceImplTest {

    @Test
    public void testUrlHeaders() throws LoanReaderService.LoanReaderException {
        long seed = System.currentTimeMillis();
        Random r = new Random(seed);

        int pageSize = 1 + r.nextInt(10);
        OffsetDateTime now = OffsetDateTime.now();
        String timeStamp = now.toString().substring(0, 23);

        RestTemplate restTemplate = mock(RestTemplate.class);
        String url = "zonky" + "?datePublished__gte=" + timeStamp;
        HttpHeaders responseHeaders = createResponseHeaders(0);

        ResponseEntity responseEntity = new ResponseEntity(emptyList(), responseHeaders, HttpStatus.OK);

        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class),
                any(ParameterizedTypeReference.class)))
                .thenReturn(responseEntity);

        LoanReaderServiceImpl underTest = new LoanReaderServiceImpl(restTemplate, "zonky", pageSize, "user");

        underTest.getLoansFrom(now);

        ArgumentCaptor<HttpEntity> httpEntityArgumentCaptor = ArgumentCaptor.forClass(HttpEntity.class);
        verify(restTemplate).exchange(eq(url), eq(HttpMethod.GET), httpEntityArgumentCaptor.capture(),
                any(ParameterizedTypeReference.class));

        HttpHeaders requestHeaders = httpEntityArgumentCaptor.getValue().getHeaders();
        assertEquals("Failed with seed " + seed, "0", requestHeaders.getFirst("X-Page"));
        assertEquals(Integer.toString(pageSize), requestHeaders.getFirst("X-Size"));
        assertEquals("datePublished", requestHeaders.getFirst("X-Order"));
        assertEquals("user", requestHeaders.getFirst("User-Agent"));
    }

    private HttpHeaders createResponseHeaders(int total) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("X-Total", Integer.toString(total));
        return responseHeaders;
    }

    @Test
    public void testMultiplePages() throws LoanReaderService.LoanReaderException {
        RestTemplate restTemplate = mock(RestTemplate.class);
        HttpHeaders responseHeaders = createResponseHeaders(3);

        Loan loan1 = createLoan(1);
        Loan loan2 = createLoan(2);
        Loan loan3 = createLoan(3);

        ResponseEntity responseEntity1 = new ResponseEntity(asList(loan1, loan2), responseHeaders, HttpStatus.OK);
        ResponseEntity responseEntity2 = new ResponseEntity(singletonList(loan3), responseHeaders, HttpStatus.OK);

        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class),
                any(ParameterizedTypeReference.class)))
                .thenReturn(responseEntity1)
                .thenReturn(responseEntity2);

        LoanReaderServiceImpl underTest = new LoanReaderServiceImpl(restTemplate, "zonky", 2, "user");

        List<Loan> loans = underTest.getLoansFrom(OffsetDateTime.now());

        assertEquals(asList(loan1, loan2, loan3), loans);
    }

    @Test
    public void testDynamicTotalSize() throws LoanReaderService.LoanReaderException {
        RestTemplate restTemplate = mock(RestTemplate.class);

        Loan loan1 = createLoan(1);
        Loan loan2 = createLoan(2);
        Loan loan3 = createLoan(3);
        Loan loan4 = createLoan(4);
        Loan loan5 = createLoan(5);

        ResponseEntity responseEntity1 = new ResponseEntity(asList(loan1, loan2), createResponseHeaders(3), HttpStatus.OK);
        ResponseEntity responseEntity2 = new ResponseEntity(asList(loan3, loan4), createResponseHeaders(5), HttpStatus.OK);
        ResponseEntity responseEntity3 = new ResponseEntity(singletonList(loan5), createResponseHeaders(5), HttpStatus.OK);

        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class),
                any(ParameterizedTypeReference.class)))
                .thenReturn(responseEntity1)
                .thenReturn(responseEntity2)
                .thenReturn(responseEntity3);

        LoanReaderServiceImpl underTest = new LoanReaderServiceImpl(restTemplate, "zonky", 2, "user");

        List<Loan> loans = underTest.getLoansFrom(OffsetDateTime.now());

        assertEquals(asList(loan1, loan2, loan3, loan4, loan5), loans);
    }

    @Test(expected = LoanReaderService.LoanReaderException.class)
    public void testException() throws LoanReaderService.LoanReaderException {
        RestTemplate restTemplate = mock(RestTemplate.class);
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class),
                any(ParameterizedTypeReference.class)))
                .thenThrow(new RestClientException(""));

        LoanReaderServiceImpl underTest = new LoanReaderServiceImpl(restTemplate, "zonky", 2, "user");
        underTest.getLoansFrom(OffsetDateTime.now());
    }

    @Test(expected = LoanReaderService.LoanReaderException.class)
    public void testNotOkStatusCode() throws LoanReaderService.LoanReaderException {
        RestTemplate restTemplate = mock(RestTemplate.class);

        ResponseEntity responseEntity = new ResponseEntity(emptyList(), new HttpHeaders(), HttpStatus.FORBIDDEN);

        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class),
                any(ParameterizedTypeReference.class)))
                .thenReturn(responseEntity);

        LoanReaderServiceImpl underTest = new LoanReaderServiceImpl(restTemplate, "zonky", 2, "user");

        List<Loan> loans = underTest.getLoansFrom(OffsetDateTime.now());
    }

    private Loan createLoan(int i) {
        Loan loan1 = new Loan();
        loan1.setId(i);
        return loan1;
    }


}