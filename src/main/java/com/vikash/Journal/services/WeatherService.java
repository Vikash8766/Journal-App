package com.vikash.Journal.services;

import com.vikash.Journal.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {

    private static final String apiKey="02bfa83304a9579d5826dc70c18679b3";

    private static final String API="http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";


    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city){

            String finalApi=API.replace("CITY", city).replace("API_KEY",apiKey);

         ResponseEntity<WeatherResponse> response= restTemplate.exchange(finalApi, HttpMethod.GET,null,WeatherResponse.class);

        WeatherResponse body=response.getBody();

        return body;



    }
}
