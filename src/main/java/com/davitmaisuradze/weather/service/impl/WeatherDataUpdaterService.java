package com.davitmaisuradze.weather.service.impl;

import com.davitmaisuradze.weather.entity.Weather;
import com.davitmaisuradze.weather.repository.WeatherRepository;
import com.davitmaisuradze.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherDataUpdaterService {
    private final WeatherRepository weatherRepository;
    private final WeatherService weatherService;

    @Scheduled(fixedRate = 30000) //1000 * 30 = 30000 => 30 seconds
    public void updateWeatherDataForAllCities() {
        List<Weather> allCities = weatherRepository.findAll();
        for (Weather cityWeather : allCities) {
            try {
                weatherService.findWeatherByCity(cityWeather.getCity());
                log.info("Weathers updated");
            } catch (Exception e) {
                log.error("Error updating weather data", e);
            }
        }
    }
}
