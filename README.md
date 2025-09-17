Weather Forecast Web Application

A simple web application built with Java and Spring Boot that displays the current weather and a multi-day forecast based on the user's current location. It uses the OpenWeatherMap One Call API 3.0 to fetch weather data.

---

Features

Fetches current weather conditions and a 7-day forecast.
Automatically detects the user's location using the browser's Geolocation API.
Presents data in a clean, interactive tabbed interface (Now, Today, Tomorrow, etc.).
All data processing and unit conversions (e.g., degrees to wind direction) are handled on the backend.

---

Technologies Used

Backend: Java 21, Spring Boot, Maven
Frontend: HTML, CSS, Vanilla JavaScript
API: OpenWeatherMap One Call API 3.0
Libraries: Lombok

---

Configuration

To run this application, you need to provide your own OpenWeatherMap API key. The application is configured to read this key from an environment variable.

Setting up the API Key in IntelliJ IDEA

1.  Get your API Key:
      Sign up for a free account on [openweathermap.org](https://openweathermap.org/) and subscribe to the "One Call API 3.0" plan (1000 calls/day are FREE!).
      Find your API key in your account's dashboard.
2.  Edit Run/Debug Configurations:
      In the top-right corner of IntelliJ, click on the dropdown menu with your application's name and select "Edit Configurations...".
3.  Find Environment Variables:**
      In the configuration window, look for the "Environment variables" field.
      Tip: If you don't see it, click on "Modify options" and select "Environment variables" to make the field visible.
4.  Add the Environment Variable:
      Type following sentence: OPENWEATHERMAP_API_KEY=yourKey
5.  Save and Apply!

---

How to Run

1.  Clone this repository.
2.  Set up the `OPENWEATHERMAP_API_KEY` environment variable as described above.
3.  Run the main application class (`WeatherAppApplication.java`) from IntelliJ IDEA.
4.  Open your web browser and navigate to `http://localhost:8080`.
