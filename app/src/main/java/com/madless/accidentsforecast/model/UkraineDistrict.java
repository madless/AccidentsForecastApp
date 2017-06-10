package com.madless.accidentsforecast.model;

/**
 * Created by User on 10.06.2017.
 */

public class UkraineDistrict {
    private String name;
    private Float latitude;
    private Float lonitude;
    private Integer accidents;
    private Integer danger;

    private Integer color;

    public UkraineDistrict(String name, Float latitude, Float lonitude, Integer accidents, Integer danger) {
        this.name = name;
        this.latitude = latitude;
        this.lonitude = lonitude;
        this.accidents = accidents;
        this.danger = danger;
    }

    public String getName() {
        return name;
    }

    public Float getLatitude() {
        return latitude;
    }

    public Float getLonitude() {
        return lonitude;
    }

    public Integer getAccidents() {
        return accidents;
    }

    public Integer getDanger() {
        return danger;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }
}
