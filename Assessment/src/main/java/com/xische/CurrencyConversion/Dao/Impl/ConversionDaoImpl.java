package com.xische.CurrencyConversion.Dao.Impl;

import com.google.gson.Gson;
import com.xische.CurrencyConversion.Dao.ConversionDao;
import com.xische.CurrencyConversion.models.ExchangeRateResponse;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;


@Slf4j
@Repository
public class ConversionDaoImpl implements ConversionDao<ExchangeRateResponse> {

    @Value("${app.url}")
    String urlString;
    private ExchangeRateResponse exchangeRateResponse;
    @Autowired
    public void loadExchangeRates(){
         exchangeRateResponse =  findExchangeRates();
    }

    @Override
    public ExchangeRateResponse findExchangeRates() {
        if(Objects.nonNull(exchangeRateResponse)){
            log.info("exchangeRateResponse : {}",exchangeRateResponse);
            return exchangeRateResponse;
        }
        return sendExchangeRateRequest();
    }

    public ExchangeRateResponse sendExchangeRateRequest(){
        // Create an HttpClient
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10)) // Connection timeout
                .build();

        log.info("URL to hit :{}", urlString);
        // Create a request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .header("Accept", "application/json")
                .GET()
                .timeout(Duration.ofSeconds(10))
                .build();
        ExchangeRateResponse exchangeRateResponse = null;
        // Send the request and handle the response
        try {
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (httpResponse.statusCode() == 200) {
                exchangeRateResponse = new Gson().fromJson(httpResponse.body(), ExchangeRateResponse.class);
            } else {
                log.error("Request failed. Response Code: {}", httpResponse.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return exchangeRateResponse;
    }

}
