package com.szepep.zonky.hw.impl;

import com.szepep.zonky.hw.api.LoanReaderService;
import com.szepep.zonky.hw.dto.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class LoanReaderServiceImpl implements LoanReaderService {

    private static final String FIELD_PUBLISHED = "datePublished";
    private static final String HEADER_SIZE = "X-Size";
    private static final String HEADER_TOTAL = "X-Total";
    private static final String HEADER_PAGE = "X-Page";
    private static final String HEADER_ORDER = "X-Order";

    private final RestTemplate restTemplate;
    private final String zonkyUrl;
    private final int pageSize;
    private final ParameterizedTypeReference<List<Loan>> responseType =
            new ParameterizedTypeReference<List<Loan>>() {
            };

    @Autowired
    public LoanReaderServiceImpl(RestTemplate restTemplate,
                                 @Value("${zonky.url}") String zonkyUrl,
                                 @Value("${zonky.pageSize}") int pageSize) {
        this.restTemplate = restTemplate;
        this.zonkyUrl = zonkyUrl;
        this.pageSize = pageSize;
    }

    @Override
    public List<Loan> getLoansFrom(OffsetDateTime from) {
        List<Object> result = new ArrayList<>();

        // Bug in RestTemplate, escaping '+' not works, using '%2B' throws exception.
        String dateTime = from.toString().substring(0, 23);

        int page = 0;
        boolean allDataRead = false;
        while (!allDataRead) {
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.add(HEADER_SIZE, Integer.toString(pageSize));
                headers.add(HEADER_PAGE, Integer.toString(page));
                headers.add(HEADER_ORDER, FIELD_PUBLISHED);
                headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

                String url = String.format("%s?%s__gte=%s", zonkyUrl, FIELD_PUBLISHED, dateTime);

                ResponseEntity<List<Loan>> responseEntity = restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        new HttpEntity<>(headers),
                        responseType);
                result.addAll(responseEntity.getBody());
                int total = Integer.parseInt(responseEntity.getHeaders().get(HEADER_TOTAL).get(0));
                allDataRead = result.size() == total;
                ++page;
            } catch (Exception e) {
                System.err.println(e);
            }
        }
        return null;
    }
}
