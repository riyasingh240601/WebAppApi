package com.OpenWeatherAPI.OpenWeatherAPI;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
@Service
@Component
public class WeatherService {
    // external 3rd party api call;
    // Httpclient

    private String getWeatherData(){
        try{
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&appid=7adf12bead8ac1624dd9406b876d2565");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responsecode = conn.getResponseCode();
            if (responsecode == 200){
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println(response.toString());
            } else if (responsecode >= 400 && responsecode <= 499) {
                return "Getting client error : 4xx";
            } else if (responsecode >= 500 && responsecode <= 599) {
                return "Getting server error : 5xx";
            }
        } catch (Exception e) {
//            log.error("getting exception: {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }
}
