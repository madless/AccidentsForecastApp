package com.madless.accidentsforecast.model;

/**
 * 6/1/2017.
 */

public class Accident {
    private int cluster;
    private float longitude;
    private float latitude;

    public Accident() {}

    public Accident(float longitude, float latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Accident(int cluster, float longitude, float latitude) {
        this.cluster = cluster;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public int getCluster() {
        return cluster;
    }

    public void setCluster(int cluster) {
        this.cluster = cluster;
    }

    @Override
    public String toString() {
        return "Accident{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
