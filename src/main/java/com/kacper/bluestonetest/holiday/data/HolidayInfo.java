package com.kacper.bluestonetest.holiday.data;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kacper.bluestonetest.holiday.data.serializer.HolidayNamesSerializer;

import java.time.LocalDate;

@JsonSerialize(using = HolidayNamesSerializer.class)
public class HolidayInfo {

    private LocalDate date;
    private String[] holidays;


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String[] getHolidays() {
        return holidays;
    }

    public void setHolidays(String[] holidays) {
        this.holidays = holidays;
    }
}
