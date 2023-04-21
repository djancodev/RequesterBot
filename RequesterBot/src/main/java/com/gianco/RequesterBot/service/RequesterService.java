package com.gianco.RequesterBot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class RequesterService {
    @Value("${url}")
    private String url;
    @Value("${token}")
    private String token;

    public String doRequest() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + token);
        httpHeaders.set("Accept","*/*");
        httpHeaders.set("Accept-Encoding", "gzip, deflate, br");
        httpHeaders.set("Connection", "keep-alive");
        httpHeaders.set("User-Agent","PostmanRuntime/7.32.2");
        httpHeaders.set("Cookie", "JSESSIONID=F4345363A19F9E45B8ECA99A66842FE3");
        httpHeaders.set("Host", "www.sanita.puglia.it");


        HttpEntity<String> httpEntity = new HttpEntity<>("", httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

        return exchange.getBody();
    }
}
