package com.madless.accidentsforecast;

import android.app.Application;
import android.content.Context;

/**
 * 6/2/2017.
 */

public class ForecastApp extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
