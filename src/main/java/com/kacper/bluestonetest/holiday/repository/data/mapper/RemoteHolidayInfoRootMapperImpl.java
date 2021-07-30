package com.kacper.bluestonetest.holiday.repository.data.mapper;

import com.kacper.bluestonetest.holiday.data.HolidayInfo;
import com.kacper.bluestonetest.holiday.repository.data.RemoteHolidayInfo;
import com.kacper.bluestonetest.holiday.repository.data.RemoteHolidayInfoRoot;
import com.kacper.bluestonetest.holiday.repository.data.SingleRemoteHoliday;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class RemoteHolidayInfoRootMapperImpl implements RemoteHolidayInfoRootMapper {

    @Override
    public HolidayInfo mapRemoteHolidayInfoRootsToHolidayInfo(List<RemoteHolidayInfoRoot> remoteHolidayInfoRoots, LocalDate holidayDate) {
        HolidayInfo holidayInfo = new HolidayInfo();
        List<SingleRemoteHoliday> singleRemoteHolidays = new ArrayList<>();
        remoteHolidayInfoRoots.stream()
                .map(RemoteHolidayInfoRoot::getResponse)
                .map(RemoteHolidayInfo::getHolidays)
                .forEach(singleRemoteHolidays::addAll);
        holidayInfo.setHolidays(singleRemoteHolidays.stream()
                .map(SingleRemoteHoliday::getName)
                .toArray(String[]::new));
        holidayInfo.setDate(holidayDate);
        return holidayInfo;
    }
}
