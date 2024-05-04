package com.davitmaisuradze.weather.service;

import com.davitmaisuradze.weather.dto.WeatherDto;

import java.util.List;

public interface WeatherService {
    WeatherDto findWeatherByCity(String city);
    List<WeatherDto> findAllWeathers();
}
