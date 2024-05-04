package com.davitmaisuradze.weather.service;

import com.davitmaisuradze.weather.dto.WeatherDto;
import com.davitmaisuradze.weather.entity.Weather;
import com.davitmaisuradze.weather.mapper.WeatherMapper;
import com.davitmaisuradze.weather.repository.WeatherRepository;
import com.davitmaisuradze.weather.service.impl.WeatherServiceImpl;
import com.davitmaisuradze.weather.utils.WeatherApiClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherServiceImplTests {
    @Mock
    private WeatherRepository weatherRepository;
    @Mock
    private WeatherApiClient weatherApiClient;
    @Mock
    private WeatherMapper weatherMapper;
    @InjectMocks
    private WeatherServiceImpl weatherService;

    @Test
    void testFindWeatherByCity() {
        WeatherDto weatherDto = new WeatherDto();
        weatherDto.setCity("Berlin");
        weatherDto.setCountry("DE");
        weatherDto.setTemperature(24.2);
        weatherDto.setHumidity(78);
        weatherDto.setWindSpeed(2.2);
        weatherDto.setDescription("Windy weather");

        Weather weather = new Weather();
        weather.setCity("Berlin");
        weather.setCountry("DE");
        weather.setTemperature(20.0);
        weather.setHumidity(85);
        weather.setWindSpeed(1.2);
        weather.setDescription("Good weather");

        when(weatherApiClient.getWeatherData(anyString())).thenReturn(weatherDto);
        when(weatherRepository.findByCity(anyString())).thenReturn(Optional.of(weather));

        WeatherDto actual = weatherService.findWeatherByCity("Berlin");

        assertNotNull(actual);
        assertEquals(weatherDto.getTemperature(), actual.getTemperature());
        assertEquals(weatherDto.getHumidity(), actual.getHumidity());
        assertEquals(weatherDto.getWindSpeed(), actual.getWindSpeed());
        assertEquals(weatherDto.getDescription(), actual.getDescription());

        verify(weatherApiClient).getWeatherData(anyString());
        verify(weatherRepository).findByCity(anyString());
    }

    @Test
    void testFindAllWeathers() {
        WeatherDto weatherDto1 = new WeatherDto();
        WeatherDto weatherDto2 = new WeatherDto();

        Weather weather1 = new Weather();
        Weather weather2 = new Weather();
        List<Weather> weathers = Arrays.asList(weather1, weather2);

        when(weatherRepository.findAll()).thenReturn(weathers);
        when(weatherMapper.weatherToWeatherDto(weather1)).thenReturn(weatherDto1);
        when(weatherMapper.weatherToWeatherDto(weather2)).thenReturn(weatherDto2);

        List<WeatherDto> actual = weatherService.findAllWeathers();

        assertNotNull(actual);
        assertEquals(2, actual.size());

        verify(weatherRepository).findAll();
        verify(weatherMapper).weatherToWeatherDto(weather1);
        verify(weatherMapper).weatherToWeatherDto(weather2);
    }
}
