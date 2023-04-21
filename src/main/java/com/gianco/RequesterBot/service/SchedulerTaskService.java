package com.gianco.RequesterBot.service;

import com.gianco.RequesterBot.dto.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class SchedulerTaskService {
    private static final Logger log = LoggerFactory.getLogger(SchedulerTaskService.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Autowired
    RequesterService requesterService;
    @Autowired
    SsnAppointmentFinder ssnAppointmentFinder;
    @Autowired
    NotifierService notifierService;

    @Scheduled(fixedRate = 300000)
    public void doRequest() {
        String response = requesterService.doRequest();
        Result result = ssnAppointmentFinder.isFound(response);
        if (result.isFound()) {
            notifierService.notify(result.getText());
        }
        log.info("Request done at {}", dateFormat.format(new Date()));
    }
}
