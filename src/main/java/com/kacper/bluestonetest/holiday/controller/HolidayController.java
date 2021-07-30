package com.kacper.bluestonetest.holiday.controller;

import com.kacper.bluestonetest.holiday.data.HolidayInfo;
import com.kacper.bluestonetest.holiday.data.HolidayRequestParams;
import com.kacper.bluestonetest.holiday.repository.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class HolidayController {

    @Autowired
    private HolidayRepository holidayRepository;

    public HolidayController(HolidayRepository holidayRepository) {
        this.holidayRepository = holidayRepository;
    }

    @GetMapping("/api/holidays")
    public ResponseEntity<HolidayInfo> getHolidays(@Valid HolidayRequestParams holidayRequestParams) {
        HolidayInfo holidayInfo = holidayRepository.findHolidays(holidayRequestParams);
        return ResponseEntity.ok(holidayInfo);
    }


}
