package com.madless.accidentsforecast.db_new;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.android.gms.maps.model.LatLng;
import com.madless.accidentsforecast.model.Accident;
import com.madless.accidentsforecast.model.Cluster;
import com.madless.accidentsforecast.model.UkraineDistrict;
import com.madless.accidentsforecast.utils.Const;
import com.madless.accidentsforecast.utils.Loggr;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 04.06.2017.
 */

public class DbAdapter {
    private Loggr logger = new Loggr(this.getClass());
    protected static final String TAG = "DataAdapter";

    private final Context mContext;
    private SQLiteDatabase db;
    private DataBaseHelper dbHelper;

    public DbAdapter(Context context) {
        this.mContext = context;
        dbHelper = new DataBaseHelper(mContext);
    }

    public DbAdapter createDatabase(InputStream inputStream) {
        try {
            dbHelper.createDataBase(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public boolean isDbExists() {
        return dbHelper.isDbExists();
    }

    public DbAdapter open() {
        try {
            dbHelper.openDataBase();
            dbHelper.close();
            db = dbHelper.getReadableDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    public void close()
    {
        dbHelper.close();
    }

    public void fillAccidents(Map<Integer, List<Accident>> accidentsByCluster, List<LatLng> points) {
        String sql ="SELECT * FROM " + Const.TABLE_NAME_ACCIDENTS;
        Cursor c = db.rawQuery(sql, null);
        if (c.moveToFirst()) {
            int clusterColIndex = c.getColumnIndex("clusters");
            int latColIndex = c.getColumnIndex("Latitude");
            int longColIndex = c.getColumnIndex("Longitude");
            do {
                int clusterId = c.getInt(clusterColIndex);
                float latitude = c.getFloat(latColIndex);
                float longitude = c.getFloat(longColIndex);
                Accident accident = new Accident(longitude, latitude);
                List<Accident> accidents = accidentsByCluster.get(clusterId);
                if(accidents == null) {
                    accidents = new ArrayList<>();
                    accidents.add(accident);
                    accidentsByCluster.put(clusterId, accidents);
                } else {
                    accidents.add(accident);
                }
                points.add(new LatLng(latitude, longitude));
            } while (c.moveToNext());
        } else {
            logger.e("No data");
        }
        c.close();
    }

    public List<LatLng> getPoints() {
        List<LatLng> points = new ArrayList<>();
        String sql ="SELECT * FROM " + Const.TABLE_NAME_ACCIDENTS;
        Cursor c = db.rawQuery(sql, null);
        if (c.moveToFirst()) {
            int longColIndex = c.getColumnIndex("Longitude");
            int latColIndex = c.getColumnIndex("Latitude");
            do {
                float longitude = c.getFloat(longColIndex);
                float latitude = c.getFloat(latColIndex);
                LatLng latLng = new LatLng(latitude, longitude);
                points.add(latLng);
            } while (c.moveToNext());
        } else {
            logger.e("No data");
        }
        c.close();
        return points;
    }

    public List<Cluster> getClusters() {
        List<Cluster> clusters = new ArrayList<>();
        String sql ="SELECT * FROM " + Const.TABLE_NAME_CLUSTERS;
        Cursor c = db.rawQuery(sql, null);
        if (c.moveToFirst()) {
            int idCol = c.getColumnIndex("id");
            int longCol = c.getColumnIndex("y");
            int latCol = c.getColumnIndex("x");
            int numCol = c.getColumnIndex("num");
            do {
                int id = c.getInt(idCol);
                float longitude = c.getFloat(longCol);
                float latitude = c.getFloat(latCol);
                int num = c.getInt(numCol);
                Cluster cluster = new Cluster(id, longitude, latitude, num);
                clusters.add(cluster);
            } while (c.moveToNext());
        } else {
            logger.e("No data");
        }
        c.close();
        return clusters;
    }

    public List<UkraineDistrict> getDistricts() {
        List<UkraineDistrict> districts = new ArrayList<>();
        String sql ="SELECT * FROM " + Const.TABLE_NAME_UKRAINE_DISTRICTS;
        Cursor c = db.rawQuery(sql, null);
        if (c.moveToFirst()) {
            int districtCol = c.getColumnIndex("district");
            int latitudeCol = c.getColumnIndex("latitude");
            int longitudeCol = c.getColumnIndex("longitude");
            int accidentsCol = c.getColumnIndex("accidents");
            int dangerCol = c.getColumnIndex("danger");
            do {
                String d = c.getString(districtCol);
                Float lat = c.getFloat(latitudeCol);
                Float lon = c.getFloat(longitudeCol);
                Integer acc = c.getInt(accidentsCol);
                Integer danger = c.getInt(dangerCol);
                UkraineDistrict district = new UkraineDistrict(d, lat, lon, acc, danger);
                districts.add(district);
            } while (c.moveToNext());
        } else {
            logger.e("No data");
        }
        c.close();
        return districts;
    }
}
