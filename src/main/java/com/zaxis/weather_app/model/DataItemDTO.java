package com.zaxis.weather_app.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DataItemDTO {
    private String label;
    private String value;
}
