package com.kacper.bluestonetest.holiday.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kacper.bluestonetest.holiday.data.HolidayInfo;
import com.kacper.bluestonetest.holiday.data.HolidayRequestParams;
import com.kacper.bluestonetest.holiday.repository.data.RemoteHolidayInfoRoot;
import com.kacper.bluestonetest.holiday.repository.data.mapper.RemoteHolidayInfoRootMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Repository
public class RemoteHolidayRepository implements HolidayRepository{

    @Autowired
    private HttpGetHolidayRequestHandlerBuilder httpGetHolidayRequestHandlerBuilder;

    @Autowired
    private RemoteHolidayInfoRootMapper remoteHolidayInfoRootMapper;

    public RemoteHolidayRepository(HttpGetHolidayRequestHandlerBuilder httpGetHolidayRequestHandlerBuilder, RemoteHolidayInfoRootMapper remoteHolidayInfoRootMapper) {
        this.httpGetHolidayRequestHandlerBuilder = httpGetHolidayRequestHandlerBuilder;
        this.remoteHolidayInfoRootMapper = remoteHolidayInfoRootMapper;
    }

    @Override
    public HolidayInfo findHolidays(HolidayRequestParams holidayRequestParams) {
        HolidayInfo holidayInfo = null;
        try {
            List<Future<String>> completableFutureResponses = sendGetHolidayRequests(holidayRequestParams);
            holidayInfo = parseCompletableFutureResponses(completableFutureResponses, holidayRequestParams);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return holidayInfo;
    }

    private List<Future<String>> sendGetHolidayRequests(HolidayRequestParams holidayRequestParams) throws InterruptedException {
        List<Callable<String>> getHolidayRequestCallables = prepareGetHolidayRequestCallables(holidayRequestParams);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Future<String>> holidayFutureResponses = executorService.invokeAll(getHolidayRequestCallables);
        return holidayFutureResponses;
    }

    private List<Callable<String>> prepareGetHolidayRequestCallables(HolidayRequestParams holidayRequestParams) {
        List<Callable<String>> getHolidayRequestCallables = new ArrayList<>();
        getHolidayRequestCallables.add(prepareGetHolidayRequestCallable(holidayRequestParams.getDate(), holidayRequestParams.getCountryCode1()));
        getHolidayRequestCallables.add(prepareGetHolidayRequestCallable(holidayRequestParams.getDate(), holidayRequestParams.getCountryCode2()));
        return getHolidayRequestCallables;
    }

    private GetHolidayRequestCallable prepareGetHolidayRequestCallable(LocalDate date, String countryCode) {
        HttpGetHolidayRequestHandler requestHandler = httpGetHolidayRequestHandlerBuilder.create();
        GetHolidayRequestCallable getHolidayRequestCallable = new GetHolidayRequestCallable(date, countryCode, requestHandler);
        return getHolidayRequestCallable;
    }

    private HolidayInfo parseCompletableFutureResponses(List<Future<String>> completableFutureResponses, HolidayRequestParams holidayRequestParams) {
        List<RemoteHolidayInfoRoot> remoteHolidayInfo = new ArrayList<>();
        for(Future<String> completableFutureResponse: completableFutureResponses) {
            remoteHolidayInfo.add(parseCompletableFutureResponse(completableFutureResponse));
        }
        return remoteHolidayInfoRootMapper.mapRemoteHolidayInfoRootsToHolidayInfo(remoteHolidayInfo, holidayRequestParams.getDate());
    }

    private RemoteHolidayInfoRoot parseCompletableFutureResponse(Future<String> holidayResponse) {
        RemoteHolidayInfoRoot remoteHolidayInfoRoot = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String holidayJson = holidayResponse.get();
            remoteHolidayInfoRoot = objectMapper.readValue(holidayJson, RemoteHolidayInfoRoot.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } finally {
            return remoteHolidayInfoRoot;
        }
    }



}
