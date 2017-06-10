package com.madless.accidentsforecast.utils;

import android.graphics.Color;

import com.madless.accidentsforecast.R;

/**
 * Created by User on 17.05.2017.
 */

public interface Const {
    double START_POINT_LAT = 53.2;
    double START_POINT_LNG = -1.6;

    /* UA */
    double START_POINT_LAT_UA = 46.9;
    double START_POINT_LNG_UA = 31.99;

    /* EXTRA */

    String EXTRA_CLUSTER_ID = "EXTRA_CLUSTER_ID";

    int START_ZOOM = 5;

    int CRUSHES_2015_SRC = R.raw.data2015;
    int DBSCAN_CLUSTERS_SRC = R.raw.dbscanclusters;

    String REGEX_INPUT_BOUNDARY_BEGINNING = "\\A";

    int[] GRADIENT_COLORS = {
            Color.rgb(103, 103, 0), // green
            Color.rgb(103, 0, 0)    // red
    };

    float[] GRADIENT_POINTS = {
            0.2f, 1f
    };

    /* Firebase */

    String STORAGE_BASE_URL = "gs://accidentsforecast.appspot.com";
    String STORAGE_DATA_FOLDER = "data";
    String STORAGE_ACCIDENTS_FILE = "Accidents_2015.csv";
    String STORAGE_DATA_FILE = "data.sqlite";

    /* Db */

    String DB_DATA_FILENAME = "data.sqlite";

    String TABLE_NAME_ACCIDENTS = "Accidents";
    String TABLE_NAME_CLUSTERS = "Clusters";
    String TABLE_NAME_UKRAINE_DISTRICTS = "UkraineDistricts";

}
