package com.gianco.RequesterBot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gianco.RequesterBot.dto.Appointment;
import com.gianco.RequesterBot.dto.Result;
import com.gianco.RequesterBot.dto.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.gianco.RequesterBot.utils.DateUtils.formatLocalDate;
import static com.gianco.RequesterBot.utils.DateUtils.getLocalDateFromJson;

@Service
public class SsnAppointmentFinder {
    private static final Logger log = LoggerFactory.getLogger(SsnAppointmentFinder.class);
    @Value("${endDate.day}")
    private int day;
    @Value("${endDate.month}")
    private int month;
    @Value("${endDate.year}")
    private int year;

    public Result isFound(String responseJson) {
        Result result = Result.builder().build();
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Response response = mapper.readValue(responseJson, Response.class);

            LocalDate endDate = LocalDate.of(year, month, day);
            List<Appointment> list = response.getAppointmentsList().stream().filter(a -> getLocalDateFromJson(a.getDate()).isBefore(endDate)).toList();

            if (!list.isEmpty()) {
                result.setFound(true);
                result.setText(getTextMessage(list));
            }
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        return result;
    }

    private String getTextMessage(List<Appointment> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("!!! POSTI DISPONIBILI !!!\n\n");
        list.forEach(a -> {
            LocalDate date = getLocalDateFromJson(a.getDate());
            sb.append(formatLocalDate(date)).append(" ").append(a.getTime()).append("\n")
                    .append(a.getLocation()).append("\n\n");
        });
        return sb.toString();
    }
}
