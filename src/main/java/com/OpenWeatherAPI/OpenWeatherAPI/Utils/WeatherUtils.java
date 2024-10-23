package com.OpenWeatherAPI.OpenWeatherAPI.Utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherUtils {

    private List<Weather> weatherList;
    @Component
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Weather{
        private int id;
        private String main;
        private String description;
    }

    private MainWeather main;

    @Component
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MainWeather{
        private double temp;
        private double feels_like;
    }
    private long dt;

}
