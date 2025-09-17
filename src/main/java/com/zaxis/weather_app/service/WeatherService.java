package com.zaxis.weather_app.service;

import com.zaxis.weather_app.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${openweathermap.api.key}")
    private String apiKey;
    private final RestTemplate restTemplate;

    private static final String API_URL = "https://api.openweathermap.org/data/3.0/onecall?lat={lat}&lon={lon}&appid={apiKey}";

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherResponse getWeather(double lat, double lon) {
        return restTemplate.getForObject(API_URL, WeatherResponse.class, lat, lon, apiKey);
    }
}
