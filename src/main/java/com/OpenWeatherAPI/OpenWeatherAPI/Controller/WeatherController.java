package com.OpenWeatherAPI.OpenWeatherAPI.Controller;
import com.OpenWeatherAPI.OpenWeatherAPI.Service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/weather")
public class WeatherController {

    private final WeatherService weatherService;
    public WeatherController(WeatherService weatherService){
        this.weatherService = weatherService;
    }

    @GetMapping("getWeather")
    public String getWeather(){
        return weatherService.getWeatherData();
    }
}
