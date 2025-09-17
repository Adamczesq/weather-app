package com.zaxis.weather_app.model;

import lombok.Data;

import java.util.List;

@Data
public class WeatherResponse {
    private List<Weather> weather;
    private Main main;
    private Wind wind;
    private String name;
}
