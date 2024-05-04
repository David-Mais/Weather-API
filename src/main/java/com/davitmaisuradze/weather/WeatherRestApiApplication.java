package com.davitmaisuradze.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EntityScan("com.davitmaisuradze.weather.entity")
public class WeatherRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherRestApiApplication.class, args);
	}
}