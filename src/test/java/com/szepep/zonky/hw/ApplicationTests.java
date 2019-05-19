package com.szepep.zonky.hw;

import com.szepep.zonky.hw.dto.Loan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @MockBean
    private RestTemplate restTemplate;

    @Test
    public void contextLoads() {
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(ParameterizedTypeReference.class)))
                .thenReturn(new ResponseEntity(Collections.<List<Loan>>emptyList(), Utils.createResponseHeaders(0), HttpStatus.OK));
    }

}
