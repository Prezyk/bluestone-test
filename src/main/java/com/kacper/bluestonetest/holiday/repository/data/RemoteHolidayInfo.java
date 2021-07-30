package com.kacper.bluestonetest.holiday.repository.data;

import com.fasterxml.jackson.annotation.*;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RemoteHolidayInfo {

    private List<SingleRemoteHoliday> holidays;

    public List<SingleRemoteHoliday> getHolidays() {
        return holidays;
    }

    public void setHolidays(List<SingleRemoteHoliday> holidays) {
        this.holidays = holidays;
    }
}
