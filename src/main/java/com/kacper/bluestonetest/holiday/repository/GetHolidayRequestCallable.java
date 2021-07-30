package com.kacper.bluestonetest.holiday.repository;

import java.time.LocalDate;
import java.util.concurrent.Callable;

public class GetHolidayRequestCallable implements Callable<String> {

    private LocalDate date;
    private String countryCode;
    private HttpGetHolidayRequestHandler requestHandler;

    public GetHolidayRequestCallable(LocalDate date, String countryCode, HttpGetHolidayRequestHandler requestHandler) {
        this.date = date;
        this.countryCode = countryCode;
        this.requestHandler = requestHandler;
    }

    @Override
    public String call() throws Exception {
        return requestHandler.sendGetHolidaysRequest(date, countryCode);
    }
}
