package com.zaxis.weather_app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class WeatherResponse {
    private double lat;
    private double lon;
    private String timezone;
    @JsonProperty("timezone_offset")
    private int timezoneOffset;
    private Current current;
    private List<Hourly> hourly;
    private List<Daily> daily;
}
