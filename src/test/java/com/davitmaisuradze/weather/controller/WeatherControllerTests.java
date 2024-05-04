package com.davitmaisuradze.weather.controller;

import com.davitmaisuradze.weather.dto.WeatherDto;
import com.davitmaisuradze.weather.service.impl.WeatherServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WeatherController.class)
class WeatherControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherServiceImpl weatherService;

    @Test
    void testGetWeather() throws Exception {
        String city = "Tbilisi";
        WeatherDto mockWeatherDto = new WeatherDto("GE", city, 15.0, 75, 5.0, "Sunny");

        when(weatherService.findWeatherByCity(city)).thenReturn(mockWeatherDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/weather/{city}", city)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.city").value(city))
                .andExpect(jsonPath("$.description").value("Sunny"));

        verify(weatherService).findWeatherByCity(city);
    }

    @Test
    public void testGetAllWeather() throws Exception {
        List<WeatherDto> weatherList = Arrays.asList(
                new WeatherDto("GE", "Tbilisi", 15.0, 75, 5.0, "Sunny"),
                new WeatherDto("US", "Atlanta", 20.0, 65, 10.0, "Partly cloudy")
        );

        when(weatherService.findAllWeathers()).thenReturn(weatherList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/weather/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].city").value("Tbilisi"))
                .andExpect(jsonPath("$[1].city").value("Atlanta"));

        verify(weatherService).findAllWeathers();
    }

}
