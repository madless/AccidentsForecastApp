package com.madless.accidentsforecast;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;
import com.madless.accidentsforecast.utils.Const;
import com.madless.accidentsforecast.utils.DBSCANCluster;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by User on 17.05.2017.
 */

@Deprecated
public class DataProvider {
    private static DataProvider dataProvider = new DataProvider();
    private  DataProvider() {}
    public static DataProvider getInstance() {
        return dataProvider;
    }

    public ArrayList<LatLng> readListCrushesByYear(int year, Context activity) throws JSONException {
        ArrayList<LatLng> list = new ArrayList<>();
        int resource = -1;
        switch (year) {
            case 2015:
                resource = Const.CRUSHES_2015_SRC;
                break;
        }
        if(resource != -1) {
            InputStream inputStream = activity.getResources().openRawResource(resource);
            String json = new Scanner(inputStream).useDelimiter(Const.REGEX_INPUT_BOUNDARY_BEGINNING).next();
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                double lat = object.getDouble("lat");
                double lng = object.getDouble("lng");
                list.add(new LatLng(lat, lng));
            }
        }
        return list;
    }

    public List<DBSCANCluster> readListDBSCANClusters(int resource, Context activity) throws JSONException {
        List<DBSCANCluster> list = new ArrayList<DBSCANCluster>();
        InputStream inputStream = activity.getResources().openRawResource(resource);
        String json = new Scanner(inputStream).useDelimiter(Const.REGEX_INPUT_BOUNDARY_BEGINNING).next();
        JSONArray array = new JSONArray(json);
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            double lat = object.getDouble("lat");
            double lng = object.getDouble("lng");
            int amountOfCrushes = object.getInt("amnt");
            list.add(new DBSCANCluster(lat, lng, amountOfCrushes));
        }
        return list;
    }
}
