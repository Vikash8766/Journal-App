package com.vikash.Journal.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Getter
@Setter
public class WeatherResponse {


    private Current current;


    @Getter
    @Setter
    public class Current{

        private int temperature;

        @JsonProperty("weather_description")
        private ArrayList<String> weatherDescriptions;

        private int feelsLike;


    }



}


