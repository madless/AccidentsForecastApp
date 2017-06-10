package com.madless.accidentsforecast.utils;

import android.content.Context;
import android.content.Intent;

import com.madless.accidentsforecast.ClusterActivity;
import com.madless.accidentsforecast.MainActivity;
import com.madless.accidentsforecast.MapsActivity;
import com.madless.accidentsforecast.UkraineMapActivity;

/**
 * 6/2/2017.
 */

public class IntentHelper {
    public static void startMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public static void startMapsActivity(Context context) {
        Intent intent = new Intent(context, MapsActivity.class);
        context.startActivity(intent);
    }

    public static void startUkrainianMapActivity(Context context) {
        Intent intent = new Intent(context, UkraineMapActivity.class);
        context.startActivity(intent);
    }

    public static void startClusterActivity(Context context, Integer clusterId) {
        Intent intent = new Intent(context, ClusterActivity.class);
        intent.putExtra(Const.EXTRA_CLUSTER_ID, clusterId);
        context.startActivity(intent);
    }
}
