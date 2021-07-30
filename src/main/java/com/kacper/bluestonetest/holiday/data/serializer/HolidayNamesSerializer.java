package com.kacper.bluestonetest.holiday.data.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.kacper.bluestonetest.holiday.data.HolidayInfo;

import java.io.IOException;

public class HolidayNamesSerializer extends JsonSerializer<HolidayInfo> {

    private static String HOLIDAY_DATE = "date";
    private static String PREFIX = "name";

    @Override
    public void serialize(HolidayInfo holidayInfo, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField(HOLIDAY_DATE, holidayInfo.getDate().toString());
        for(int index=1; index<=holidayInfo.getHolidays().length; index++) {
            jsonGenerator.writeStringField(PREFIX+index, holidayInfo.getHolidays()[index-1]);
        }
        jsonGenerator.writeEndObject();
    }
}
