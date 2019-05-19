package com.szepep.zonky.hw;

import com.szepep.zonky.hw.dto.Loan;
import org.springframework.http.HttpHeaders;

import java.time.OffsetDateTime;

public class Utils {
    public static HttpHeaders createResponseHeaders(int total) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("X-Total", Integer.toString(total));
        return responseHeaders;
    }

    public static Loan createLoan(int id) {
        Loan loan = new Loan();
        loan.setId(id);
        loan.setDatePublished(OffsetDateTime.now().toString());
        return loan;
    }

    public static Loan createLoan(int id, String published) {
        Loan loan = new Loan();
        loan.setId(id);
        loan.setDatePublished(published);
        return loan;
    }
}
