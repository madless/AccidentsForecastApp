package com.madless.accidentsforecast.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by User on 05.06.2017.
 */

public class Cluster implements ClusterItem {
    private int id;
    private LatLng position;
    private int num;

    public Cluster(int id, float latitude, float longitude, int num) {
        this.id = id;
        position = new LatLng(latitude, longitude);
        this.num = num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public LatLng getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Cluster{" +
                "id=" + id +
                ", position=" + position +
                ", num=" + num +
                '}';
    }
}
