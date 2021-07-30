package com.kacper.bluestonetest.holiday.repository;

import com.kacper.bluestonetest.holiday.data.HolidayInfo;
import com.kacper.bluestonetest.holiday.data.HolidayRequestParams;

public interface HolidayRepository {
    HolidayInfo findHolidays(HolidayRequestParams holidayRequest);
}
