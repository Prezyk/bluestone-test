package com.kacper.bluestonetest.holiday.repository;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;

public class HttpGetHolidayRequestHandler {

    private String holidayRequestUrl;
    private String paramYearKey;
    private String paramMonthKey;
    private String paramDayKey;
    private String paramCountryCodeKey;
    private String paramApiKeyKey;
    private String paramApiKeyValue;

    public HttpGetHolidayRequestHandler(String holidayRequestUrl, String paramYearKey, String paramMonthKey, String paramDayKey, String paramCountryCodeKey, String paramApiKeyKey, String paramApiKeyValue) {
        this.holidayRequestUrl = holidayRequestUrl;
        this.paramYearKey = paramYearKey;
        this.paramMonthKey = paramMonthKey;
        this.paramDayKey = paramDayKey;
        this.paramCountryCodeKey = paramCountryCodeKey;
        this.paramApiKeyKey = paramApiKeyKey;
        this.paramApiKeyValue = paramApiKeyValue;
    }

    public String sendGetHolidaysRequest(LocalDate date, String countryCode) throws IOException, InterruptedException {
        HttpGet request = new HttpGet(buildURI(date, countryCode));
        request.addHeader("Accept", "application/json");
        CloseableHttpClient client = HttpClientBuilder.create().build();
        CloseableHttpResponse response = client.execute(request);
        String responseBody = null;
        if(response.getStatusLine().getStatusCode()==200) {
            responseBody = EntityUtils.toString(response.getEntity());
        }
        response.close();
        client.close();
        return responseBody;
    }

    private URI buildURI(LocalDate date, String countryCode) {
        URI uri = UriComponentsBuilder.fromUriString(holidayRequestUrl)
                .queryParam(paramApiKeyKey, paramApiKeyValue)
                .queryParam(paramYearKey, date.getYear())
                .queryParam(paramMonthKey, date.getMonthValue())
                .queryParam(paramDayKey, date.getDayOfMonth())
                .queryParam(paramCountryCodeKey, countryCode)
                .build()
                .toUri();
        return uri;
    }
}
