package com.davitmaisuradze.weather.service.impl;

import com.davitmaisuradze.weather.dto.WeatherDto;
import com.davitmaisuradze.weather.entity.Weather;
import com.davitmaisuradze.weather.mapper.WeatherMapper;
import com.davitmaisuradze.weather.repository.WeatherRepository;
import com.davitmaisuradze.weather.service.WeatherService;
import com.davitmaisuradze.weather.utils.WeatherApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {
    private final WeatherRepository weatherRepository;
    private final WeatherApiClient weatherApiClient;
    private final WeatherMapper weatherMapper;

    @Override
    public WeatherDto findWeatherByCity(String city) {
        WeatherDto dto = weatherApiClient.getWeatherData(city);
        Weather weather = weatherRepository.findByCity(city).orElse(null);

        if (weather != null) {
            weather.setCountry(dto.getCountry());
            weather.setTemperature(dto.getTemperature());
            weather.setHumidity(dto.getHumidity());
            weather.setWindSpeed(dto.getWindSpeed());
            weather.setDescription(dto.getDescription());
        } else {
            weather = Weather.builder()
                    .country(dto.getCountry())
                    .city(dto.getCity())
                    .temperature(dto.getTemperature())
                    .humidity(dto.getHumidity())
                    .windSpeed(dto.getWindSpeed())
                    .description(dto.getDescription())
                    .build();
        }

        weatherRepository.save(weather);
        return dto;
    }

    @Override
    public List<WeatherDto> findAllWeathers() {
        List<Weather> weathers = weatherRepository.findAll();
        return weathers
                .stream()
                .map(weatherMapper::weatherToWeatherDto)
                .toList();
    }
}
