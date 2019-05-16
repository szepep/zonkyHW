package com.szepep.zonky.hw.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.szepep.zonky.hw.api.LoanWriterService;
import com.szepep.zonky.hw.dto.Loan;
import org.springframework.stereotype.Service;

@Service
public class LoanWriterServiceImpl implements LoanWriterService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public LoanWriterServiceImpl() {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public void writeLoan(Loan loan) throws LoanWriterException {
        try {
            String asString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(loan);
            System.out.println(asString);
        } catch (JsonProcessingException e) {
            throw new LoanWriterException("Unable to print loan", e);
        }
    }
}
