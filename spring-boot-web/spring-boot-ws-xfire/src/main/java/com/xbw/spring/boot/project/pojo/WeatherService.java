package com.xbw.spring.boot.project.pojo;

/**
 * http://localhost:8080/services/WeatherService
 * http://localhost:8080/services/WeatherService?wsdl
 */
public class WeatherService {
    Weather weather;

    public Weather getWeather() {
        if (this.weather == null) {
            return new Weather();
        }
        return this.weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }
}