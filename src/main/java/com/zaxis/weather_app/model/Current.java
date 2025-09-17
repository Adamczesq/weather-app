package com.zaxis.weather_app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Current {
    private long dt;
    private long sunrise;
    private long sunset;
    private double temp;
    @JsonProperty("feels_like")
    private double feelsLike;
    private int pressure;
    private int humidity;
    private double uvi;
    private int clouds;
    private int visibility;
    @JsonProperty("wind_speed")
    private double windSpeed;
    @JsonProperty("wind_deg")
    private int windDeg;
    private List<Weather> weather;
}
