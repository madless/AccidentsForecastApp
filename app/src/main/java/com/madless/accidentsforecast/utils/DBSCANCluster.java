package com.madless.accidentsforecast.utils;

/**
 * Created by User on 17.05.2017.
 */

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class DBSCANCluster implements ClusterItem {
    private final LatLng position;
    private int amountOfCrushes;

    public DBSCANCluster(double lat, double lng, int amountOfCrushes) {
        position = new LatLng(lat, lng);
        this.amountOfCrushes = amountOfCrushes;
    }

    public int getAmountOfCrushes() {
        return amountOfCrushes;
    }

    @Override
    public LatLng getPosition() {
        return position;
    }
}