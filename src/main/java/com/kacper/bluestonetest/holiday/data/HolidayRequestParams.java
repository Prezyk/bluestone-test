package com.kacper.bluestonetest.holiday.data;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.time.LocalDate;

public class HolidayRequestParams {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Size(min = 2, max = 2)
    private String countryCode1;

    @Size(min = 2, max = 2)
    private String countryCode2;


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCountryCode1() {
        return countryCode1;
    }

    public void setCountryCode1(String countryCode1) {
        this.countryCode1 = countryCode1;
    }

    public String getCountryCode2() {
        return countryCode2;
    }

    public void setCountryCode2(String countryCode2) {
        this.countryCode2 = countryCode2;
    }
}
