package com.davitmaisuradze.weather.repository;

import com.davitmaisuradze.weather.entity.Weather;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Sql(scripts = "/test-schema.sql")
class WeatherRepositoryTests {
    @Autowired
    private WeatherRepository weatherRepository;

    @Test
    void whenFindByCity_thenReturnWeather() {
        Optional<Weather> found = weatherRepository.findByCity("Tokyo");

        assertThat(found).isPresent();
        assertEquals("JP", found.get().getCountry());
    }
}