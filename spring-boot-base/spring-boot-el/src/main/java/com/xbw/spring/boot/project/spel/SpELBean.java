package com.xbw.spring.boot.project.spel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author xbw
 */
@Component
public class SpELBean {
    @Value("#{T(java.lang.Math).PI}")
    private double pi;
    @Value("#{T(java.lang.Math).random()}")
    private double random;
    @Value("#{T(java.io.File).separator}")
    private String separator;
    @Value("#{systemProperties['java.version']}")
    private String javaVersion;

    @Value("${spring.application.name}")
    private String appName;

    public String getJavaVersion() {
        return this.javaVersion;
    }

    public double getPi() {
        return pi;
    }

    public void setPi(double pi) {
        this.pi = pi;
    }

    public double getRandom() {
        return random;
    }

    public void setRandom(double random) {
        this.random = random;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public void setJavaVersion(String javaVersion) {
        this.javaVersion = javaVersion;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
