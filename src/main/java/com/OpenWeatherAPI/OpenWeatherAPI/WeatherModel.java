package com.OpenWeatherAPI.OpenWeatherAPI;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @JsonProperty("id")
    private String id;

    @JsonProperty("main")
    private String main;
    @JsonProperty("temp")
    private String temp;
    @JsonProperty("feels_like")
    private String feels_like;
    @JsonProperty("dt")
    private String dt;
}
