package com.davitmaisuradze.weather.controller;

import com.davitmaisuradze.weather.dto.WeatherDto;
import com.davitmaisuradze.weather.service.impl.WeatherServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/weather")
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherServiceImpl weatherService;

    @GetMapping("/{city}")
    public ResponseEntity<WeatherDto> getWeather(
            @PathVariable String city
    ) {
        return ResponseEntity.ok(weatherService.findWeatherByCity(city));
    }

    @GetMapping("/all")
    public ResponseEntity<List<WeatherDto>> getAllWeather() {
        return ResponseEntity.ok(weatherService.findAllWeathers());
    }
}
