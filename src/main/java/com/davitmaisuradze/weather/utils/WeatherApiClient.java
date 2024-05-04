package com.davitmaisuradze.weather.utils;

import com.davitmaisuradze.weather.dto.WeatherDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
@Slf4j
public class WeatherApiClient {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    @Value("${weather.api.url}")
    private String weatherApiUrl;
    @Value("${weather.api.units}")
    private String units;
    @Value("${weather.api.key}")
    private String apiKey;

    public WeatherDto getWeatherData(String city) {
        String url = UriComponentsBuilder.fromHttpUrl(weatherApiUrl)
                .queryParam("q", city)
                .queryParam("units", units)
                .queryParam("appid", apiKey)
                .toUriString();
        String json = restTemplate.getForObject(url, String.class);
        WeatherDto weatherDto = new WeatherDto();
        try {
            weatherDto = convertJsonToWeatherDto(json);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        return weatherDto;
    }

    public WeatherDto convertJsonToWeatherDto(String json) throws JsonProcessingException {
        var rootNode = objectMapper.readTree(json);
        return WeatherDto
                .builder()
                .country(rootNode.path("sys").path("country").asText())
                .city(rootNode.path("name").asText())
                .temperature(rootNode.path("main").path("temp").asDouble())
                .humidity(rootNode.path("main").path("humidity").asInt())
                .windSpeed(rootNode.path("wind").path("speed").asDouble())
                .description(rootNode.path("weather").get(0).path("description").asText())
                .build();
    }
}
