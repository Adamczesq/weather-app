package com.zaxis.weather_app.model;

import lombok.Data;

import java.util.List;

@Data
public class WeatherCardDTO {
    private String title;
    private List<DataItemDTO> dataPoints;
}
