package com.kacper.bluestonetest.holiday.repository.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RemoteHolidayInfoRoot {

    private RemoteHolidayInfo response;

    public RemoteHolidayInfo getResponse() {
        return response;
    }

    public void setResponse(RemoteHolidayInfo response) {
        this.response = response;
    }
}
