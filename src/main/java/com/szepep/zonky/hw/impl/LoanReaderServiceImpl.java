package com.szepep.zonky.hw.impl;

import com.szepep.zonky.hw.api.LoanReaderService;
import com.szepep.zonky.hw.dto.Loan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class LoanReaderServiceImpl implements LoanReaderService {

    private static final Logger log = LoggerFactory.getLogger(LoanReaderServiceImpl.class);

    private static final String FIELD_PUBLISHED = "datePublished";
    private static final String HEADER_SIZE = "X-Size";
    private static final String HEADER_TOTAL = "X-Total";
    private static final String HEADER_PAGE = "X-Page";
    private static final String HEADER_ORDER = "X-Order";
    private static final String HEADER_USER_AGENT = "User-Agent";

    private final RestTemplate restTemplate;
    private final String zonkyUrl;
    private final int pageSize;
    private final String userAgent;
    private final ParameterizedTypeReference<List<Loan>> responseType =
            new ParameterizedTypeReference<List<Loan>>() {
            };

    @Autowired
    LoanReaderServiceImpl(RestTemplate restTemplate,
                          @Value("${zonky.url}") String zonkyUrl,
                          @Value("${zonky.pageSize}") int pageSize,
                          @Value("${userAgent}") String userAgent) {
        this.restTemplate = restTemplate;
        this.zonkyUrl = zonkyUrl;
        this.pageSize = pageSize;
        this.userAgent = userAgent;
    }

    @Override
    public List<Loan> getLoansFrom(OffsetDateTime from) throws LoanReaderException {
        List<Loan> result = new ArrayList<>();

        // Bug in RestTemplate, escaping '+' not works, using '%2B' throws exception.
        String dateTime = from.toString().substring(0, 23);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HEADER_SIZE, Integer.toString(pageSize));
        headers.add(HEADER_ORDER, FIELD_PUBLISHED);
        headers.add(HEADER_USER_AGENT, userAgent);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        int page = 0;
        boolean allDataRead = false;
        while (!allDataRead) {
            headers.set(HEADER_PAGE, Integer.toString(page));
            String url = String.format("%s?%s__gte=%s", zonkyUrl, FIELD_PUBLISHED, dateTime);
            String requestMsg = String.format("URL: %s\nheaders: %s", url, headers);
            try {
                log.info("Executing HTTP GET\n" + requestMsg);
                ResponseEntity<List<Loan>> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
                        new HttpEntity<>(headers), responseType);
                if (!responseEntity.getStatusCode().is2xxSuccessful()) {
                    throw new LoanReaderException(String.format("Server returned %d while reading loans\n%s",
                            responseEntity.getStatusCodeValue(), requestMsg));
                }

                result.addAll(responseEntity.getBody());
                int total = Integer.parseInt(responseEntity.getHeaders().get(HEADER_TOTAL).get(0));
                allDataRead = result.size() == total;
                ++page;
            } catch (RestClientException e) {
                throw new LoanReaderException(String.format("Unable to read loan\n%s", requestMsg), e);
            }
        }
        return result;
    }
}
