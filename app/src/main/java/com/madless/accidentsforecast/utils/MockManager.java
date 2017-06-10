package com.madless.accidentsforecast.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 6/1/2017.
 */

public class MockManager {

    private Loggr loggr = new Loggr(this.getClass());
    private final String DATA_FILE_NAME = "Accidents_2015_test.csv";
    private static MockManager instance = new MockManager();

    private MockManager() {}

    public static MockManager get() {
        return instance;
    }

    public void printData(Context context) {
        List<String> data = getData(context);
        Loggr loggr = new Loggr(this.getClass());
    }

    public List<String> getData(Context context) {
        InputStream stream = null;
        ArrayList<String> list = new ArrayList<>();
        try {
            stream = context.getAssets().open(DATA_FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }

        DataInputStream dataStream = new DataInputStream(stream);
        String data = "";
        try {
            while ((data = dataStream.readLine()) != null) {
                list.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<String> getData(InputStream stream) {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String data;
        try {
            while ((data = reader.readLine()) != null) {
                list.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void checkData(InputStream stream) {
        try {
            int c = stream.read();
            loggr.d("read: " + c);
            while (c != -1) {
                loggr.d("read: " + (char) c);
                c = stream.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
