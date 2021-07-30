package com.kacper.bluestonetest.holiday.repository.data.mapper;

import com.kacper.bluestonetest.holiday.data.HolidayInfo;
import com.kacper.bluestonetest.holiday.repository.data.RemoteHolidayInfoRoot;

import java.time.LocalDate;
import java.util.List;

public interface RemoteHolidayInfoRootMapper {
    HolidayInfo mapRemoteHolidayInfoRootsToHolidayInfo(List<RemoteHolidayInfoRoot> remoteHolidayInfoRoots, LocalDate holidayDate);
}
