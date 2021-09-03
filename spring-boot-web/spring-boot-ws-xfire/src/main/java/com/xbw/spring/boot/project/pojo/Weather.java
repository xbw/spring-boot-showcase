package com.xbw.spring.boot.project.pojo;

public class Weather {
    float temperature;
    String forecast;
    boolean rain;
    float howMuchRain;

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temp) {
        temperature = temp;
    }

    public String getForecast() {
        return forecast;
    }

    public void setForecast(String fore) {
        forecast = fore;
    }

    public boolean getRain() {
        return rain;
    }

    public void setRain(boolean r) {
        rain = r;
    }

    public float getHowMuchRain() {
        return howMuchRain;
    }

    public void setHowMuchRain(float howMuch) {
        howMuchRain = howMuch;
    }
}