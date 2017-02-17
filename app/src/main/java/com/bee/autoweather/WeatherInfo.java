package com.bee.autoweather;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Generated;

/**
 * Created by barisemreefe on 09/02/2017.
 */
@Entity
public class WeatherInfo {

    @Id
    private long timestamp;
    private String city;
    private String temperature;



    @Generated(hash = 474812552)
    public WeatherInfo(long timestamp, String city, String temperature) {
        this.timestamp = timestamp;
        this.city = city;
        this.temperature = temperature;
    }

    @Generated(hash = 477552911)
    public WeatherInfo() {
    }

    

    public long getTimestamp() {
        return timestamp;
    }

    public String getCity() {
        return city;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
