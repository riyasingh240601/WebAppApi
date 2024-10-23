package com.OpenWeatherAPI.OpenWeatherAPI.Service;

import com.OpenWeatherAPI.OpenWeatherAPI.Model.WeatherModel;
import com.OpenWeatherAPI.OpenWeatherAPI.Repo.WeatherRepo;
import com.OpenWeatherAPI.OpenWeatherAPI.Utils.WeatherUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

@Slf4j
@Service
@Component
public class WeatherService {

    private static WeatherModel weatherModel;
    private static WeatherRepo weatherRepo;
    private static ObjectMapper objectMapper;

    public WeatherService(WeatherModel weatherModel, WeatherRepo weatherRepo, ObjectMapper ObjectMapper) {
        this.weatherModel = weatherModel;
        this.weatherRepo = weatherRepo;
        this.objectMapper = ObjectMapper;
    }

    public String getWeatherData(){
        try{
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&appid=7adf12bead8ac1624dd9406b876d2565");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responsecode = conn.getResponseCode();
            if (responsecode == 200){
                System.out.println(conn.getResponseMessage() + " " + conn.getInputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                WeatherUtils weatherUtils = objectMapper.readValue(response.toString(), WeatherUtils.class);
                WeatherModel weatherModel = getWeatherModel(weatherUtils);
                weatherRepo.save(weatherModel);
                return "Successfully saved weather data into DB";
            } else if (responsecode >= 400 && responsecode <= 499) {
                return "Getting client error : 4xx";
            } else if (responsecode >= 500 && responsecode <= 599) {
                return "Getting server error : 5xx";
            }
        } catch (Exception e) {
            log.error("getting exception: {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }

    public WeatherModel getWeatherModel(WeatherUtils weatherUtils){
        WeatherModel weatherModel = new WeatherModel();
        weatherModel.setDt(weatherUtils.getDt());
        if(Objects.nonNull(weatherUtils.getMain())) {
            weatherModel.setTemp(weatherUtils.getMain().getTemp());
            weatherModel.setFeels_like(weatherUtils.getMain().getFeels_like());
        }
        if(Objects.nonNull(weatherUtils.getWeatherList()) && !weatherUtils.getWeatherList().isEmpty()) {
            weatherModel.setMain(weatherUtils.getWeatherList().get(0).getMain());
        }
        return weatherModel;
    }
}
