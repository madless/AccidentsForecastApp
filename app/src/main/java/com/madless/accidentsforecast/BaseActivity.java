package com.madless.accidentsforecast;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.madless.accidentsforecast.utils.Loggr;

/**
 * 6/2/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected Loggr loggr = new Loggr(this.getClass());
    protected Handler handler = new Handler();
}
