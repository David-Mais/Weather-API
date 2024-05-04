package com.davitmaisuradze.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WeatherDto {
    private String country;
    private String city;
    private Double temperature;
    private Integer humidity;
    private Double windSpeed;
    private String description;
}
