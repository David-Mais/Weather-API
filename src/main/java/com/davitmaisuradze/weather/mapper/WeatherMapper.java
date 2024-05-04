package com.davitmaisuradze.weather.mapper;

import com.davitmaisuradze.weather.dto.WeatherDto;
import com.davitmaisuradze.weather.entity.Weather;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WeatherMapper {
    WeatherMapper INSTANCE = Mappers.getMapper(WeatherMapper.class);
    WeatherDto weatherToWeatherDto(Weather weather);
}
