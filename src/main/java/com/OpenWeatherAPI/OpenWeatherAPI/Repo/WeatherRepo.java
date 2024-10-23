package com.OpenWeatherAPI.OpenWeatherAPI.Repo;
import com.OpenWeatherAPI.OpenWeatherAPI.Model.WeatherModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepo extends JpaRepository<WeatherModel, Integer> {
}
