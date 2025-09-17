package com.zaxis.weather_app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Hourly {
    private long dt;
    private double temp;
    @JsonProperty("feels_like")
    private double feelsLike;
    private int pressure;
    private int humidity;
    @JsonProperty("dew_point")
    private double dewPoint;
    private double uvi;
    private int clouds;
    private int visibility;
    @JsonProperty("wind_speed")
    private double windSpeed;
    @JsonProperty("wind_deg")
    private int windDeg;
    @JsonProperty("wind_gust")
    private double windGust;
    private List<Weather> weather;
    private double pop;
}