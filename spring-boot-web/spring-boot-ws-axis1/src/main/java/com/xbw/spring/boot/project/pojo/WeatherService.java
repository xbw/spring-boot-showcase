package com.xbw.spring.boot.project.pojo;

/**
 * https://axis.apache.org/axis2/java/core/docs/pojoguide.html
 * <p>
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