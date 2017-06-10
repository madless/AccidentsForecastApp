package com.madless.accidentsforecast.utils;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.heatmaps.HeatmapTileProvider;

import java.util.List;

/**
 * Created by User on 17.05.2017.
 */

public class HeatMapHelper {

    public void addHeatMap(List<LatLng> list, GoogleMap map) {
        //Gradient gradient = new Gradient(Const.GRADIENT_COLORS, Const.GRADIENT_POINTS);
        HeatmapTileProvider provider = new HeatmapTileProvider.Builder()
                .data(list)
                .build();
        map.addTileOverlay(new TileOverlayOptions().tileProvider(provider));
    }
}
