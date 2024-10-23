package com.OpenWeatherAPI.OpenWeatherAPI.Model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
@Entity
@Table(name="weather_model")
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private int id;

    @JsonProperty("main")
    private String main;
    @JsonProperty("temp")
    private double temp;
    @JsonProperty("feels_like")
    private double feels_like;
    @JsonProperty("dt")
    private long dt;
}
