package com.zaxis.weather_app.service;

import com.zaxis.weather_app.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class WeatherService {

    @Value("${openweathermap.api.key}")
    private String apiKey;
    private final RestTemplate restTemplate;

    private static final String API_URL = "https://api.openweathermap.org/data/3.0/onecall?lat={lat}&lon={lon}&units=metric&lang=pl&appid={apiKey}";

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<WeatherCardDTO> getWeatherOverview(double lat, double lon) {
        List<WeatherCardDTO> weatherCards = new ArrayList<>();

        WeatherCardDTO nowCard = new WeatherCardDTO();
        nowCard.setTitle("Teraz");

        WeatherResponse weather = getWeather(lat, lon);

        List<DataItemDTO> nowDataPoints = new ArrayList<>();
        nowDataPoints.add(new DataItemDTO("Dzień tygodnia", setFirstUpperCase(LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.of("pl", "PL")))));
        nowDataPoints.add(new DataItemDTO("Data", LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)));
        nowDataPoints.add(new DataItemDTO("Godzina", convertTimestampToTime(weather.getCurrent().getDt())));
        nowDataPoints.add(new DataItemDTO("Temperatura", weather.getCurrent().getTemp() + "°C"));
        nowDataPoints.add(new DataItemDTO("Odczuwalna", weather.getCurrent().getFeelsLike() + "°C"));
        nowDataPoints.add(new DataItemDTO("Opis", weather.getCurrent().getWeather().getFirst().getDescription()));
        nowDataPoints.add(new DataItemDTO("Prędkość wiatru", weather.getCurrent().getWindSpeed() + " m/s"));
        nowDataPoints.add(new DataItemDTO("Kierunek wiatru", convertDegreesToDirection(weather.getCurrent().getWindDeg())));
        nowDataPoints.add(new DataItemDTO("Wilgotność", weather.getCurrent().getHumidity() + "%"));
        nowDataPoints.add(new DataItemDTO("Ciśnienie", weather.getCurrent().getPressure() + ""));

        nowCard.setDataPoints(nowDataPoints);
        weatherCards.add(nowCard);

        weatherCards.addAll(getDailyWeatherCards(weather.getDaily()));
        //TODO add yesterday card
        return weatherCards;
    }

    private List<WeatherCardDTO> getDailyWeatherCards(List<Daily> dailyList) {
        List<WeatherCardDTO> weatherCards = new ArrayList<>();

        for (int i = 0; i < dailyList.size(); i++) {
            Daily daily = dailyList.get(i);
            List<DataItemDTO> dataPoints = new ArrayList<>();

            String title;
            LocalDate date = LocalDate.now().plusDays(i);
            if (i == 0) {
                title = "Dziś";
            } else if (i == 1) {
                title = "Jutro";
            } else if (i == 2) {
                title = "Pojutrze";
            } else {
                title = setFirstUpperCase(date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.of("pl", "PL")));
            }

            if (i <= 2) dataPoints.add(new DataItemDTO("Dzień tygodnia", setFirstUpperCase(date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.of("pl", "PL")))));

            dataPoints.add(new DataItemDTO("Data", date.format(DateTimeFormatter.ISO_LOCAL_DATE)));
            dataPoints.add(new DataItemDTO("Temperatura Min.", daily.getTemp().getMin() + "°C"));
            dataPoints.add(new DataItemDTO("Temperatura Max.", daily.getTemp().getMax() + "°C"));
            dataPoints.add(new DataItemDTO("Odczuwalna dzień", daily.getFeelsLike().getDay() + "°C"));
            dataPoints.add(new DataItemDTO("Odczuwalna noc", daily.getFeelsLike().getNight() + "°C"));
            dataPoints.add(new DataItemDTO("Opis", daily.getWeather().getFirst().getDescription()));
            dataPoints.add(new DataItemDTO("Prędkość wiatru", daily.getWindSpeed() + " m/s"));
            dataPoints.add(new DataItemDTO("Kierunek wiatru", convertDegreesToDirection(daily.getWindDeg())));
            dataPoints.add(new DataItemDTO("Wilgotność", daily.getHumidity() + "%"));
            dataPoints.add(new DataItemDTO("Ciśnienie", String.valueOf(daily.getPressure())));
            dataPoints.add(new DataItemDTO("Wschód", convertTimestampToTime(daily.getSunrise())));
            dataPoints.add(new DataItemDTO("Zachód", convertTimestampToTime(daily.getSunset())));

            WeatherCardDTO card = new WeatherCardDTO();
            card.setDataPoints(dataPoints);
            card.setTitle(title);
            weatherCards.add(card);
        }

        return weatherCards;
    }

    private WeatherResponse getWeather(double lat, double lon) {
        return restTemplate.getForObject(API_URL, WeatherResponse.class, lat, lon, apiKey);
    }

    private String convertTimestampToTime(long unixTimestamp) {
        Instant instant = Instant.ofEpochSecond(unixTimestamp);
        ZoneId userZone = ZoneId.of("Europe/Warsaw");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return instant.atZone(userZone).format(formatter);
    }

    private String convertDegreesToDirection(int degrees) {
        String[] directions = {"N", "NE", "E", "SE", "S", "SW", "W", "NW"};
        int index = (int) Math.round((double) degrees / 45) % 8;

        return directions[index];
    }

    private String setFirstUpperCase(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }
}
