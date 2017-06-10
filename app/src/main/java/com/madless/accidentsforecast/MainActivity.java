package com.madless.accidentsforecast;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.madless.accidentsforecast.utils.IntentHelper;
import com.madless.accidentsforecast.utils.Loggr;

/**
 * 6/1/2017.
 */

public class MainActivity extends AppCompatActivity {
    private Loggr loggr = new Loggr(this.getClass());
    private Button buttonLoadData;
    private Button buttonOpenMap;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLoadData = (Button) findViewById(R.id.buttonLoadData);
        buttonOpenMap = (Button) findViewById(R.id.buttonOpenMap);

        buttonOpenMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentHelper.startMapsActivity(MainActivity.this);
            }
        });
        buttonLoadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentHelper.startUkrainianMapActivity(MainActivity.this);
            }
        });
    }
}
