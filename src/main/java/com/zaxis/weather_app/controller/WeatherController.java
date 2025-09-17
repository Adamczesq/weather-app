package com.zaxis.weather_app.controller;

import com.zaxis.weather_app.model.WeatherResponse;
import com.zaxis.weather_app.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/api/weather")
    public WeatherResponse getWeather(@RequestParam double lat, @RequestParam double lon) {
        return weatherService.getWeather(lat, lon);
    }
}
