package com.zaxis.weather_app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Daily {
    private long dt;
    private long sunrise;
    private long sunset;
    private long moonrise;
    private long moonset;
    @JsonProperty("moon_phase")
    private double moonPhase;
    private String summary;
    private Temp temp;
    @JsonProperty("feels_like")
    private FeelsLike feelsLike;
    private int pressure;
    private int humidity;
    @JsonProperty("dew_point")
    private double dewPoint;
    @JsonProperty("wind_speed")
    private double windSpeed;
    @JsonProperty("wind_deg")
    private int windDeg;
    @JsonProperty("wind_gust")
    private double windGust;
    private List<Weather> weather;
    private int clouds;
    private double pop;
    private double uvi;
}