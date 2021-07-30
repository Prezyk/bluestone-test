package com.kacper.bluestonetest.holiday.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HttpGetHolidayRequestHandlerBuilder {

    @Value("${holiday.request.url}")
    private String holidayRequestUrl;

    @Value("${holiday.request.paramKey.year}")
    private String paramYearKey;

    @Value("${holiday.request.paramKey.month}")
    private String paramMonthKey;

    @Value("${holiday.request.paramKey.day}")
    private String paramDayKey;

    @Value("${holiday.request.paramKey.countryCode}")
    private String paramCountryCodeKey;

    @Value("${holiday.request.paramKey.apiKey}")
    private String paramApiKeyKey;

    @Value("${holiday.request.paramValue.apiKey}")
    private String paramApiKeyValue;

    public HttpGetHolidayRequestHandlerBuilder() {
    }

    public HttpGetHolidayRequestHandler create() {
        return new HttpGetHolidayRequestHandler(holidayRequestUrl, paramYearKey, paramMonthKey, paramDayKey, paramCountryCodeKey, paramApiKeyKey, paramApiKeyValue);
    }

    public String getHolidayRequestUrl() {
        return holidayRequestUrl;
    }

    public void setHolidayRequestUrl(String holidayRequestUrl) {
        this.holidayRequestUrl = holidayRequestUrl;
    }

    public String getParamYearKey() {
        return paramYearKey;
    }

    public void setParamYearKey(String paramYearKey) {
        this.paramYearKey = paramYearKey;
    }

    public String getParamMonthKey() {
        return paramMonthKey;
    }

    public void setParamMonthKey(String paramMonthKey) {
        this.paramMonthKey = paramMonthKey;
    }

    public String getParamDayKey() {
        return paramDayKey;
    }

    public void setParamDayKey(String paramDayKey) {
        this.paramDayKey = paramDayKey;
    }

    public String getParamCountryCodeKey() {
        return paramCountryCodeKey;
    }

    public void setParamCountryCodeKey(String paramCountryCodeKey) {
        this.paramCountryCodeKey = paramCountryCodeKey;
    }

    public String getParamApiKeyKey() {
        return paramApiKeyKey;
    }

    public void setParamApiKeyKey(String paramApiKeyKey) {
        this.paramApiKeyKey = paramApiKeyKey;
    }

    public String getParamApiKeyValue() {
        return paramApiKeyValue;
    }

    public void setParamApiKeyValue(String paramApiKeyValue) {
        this.paramApiKeyValue = paramApiKeyValue;
    }
}
