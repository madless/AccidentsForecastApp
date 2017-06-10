package com.madless.accidentsforecast;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;
import com.madless.accidentsforecast.utils.Const;
import com.madless.accidentsforecast.utils.DBSCANCluster;

import org.json.JSONException;

import java.util.HashMap;
import java.util.List;

/**
 * Created by User on 17.05.2017.
 */

@Deprecated
public class DataContainer {
    private static DataContainer dataContainer = new DataContainer();

    private HashMap<Integer, List<LatLng>> crushesMap = new HashMap<>();
    List<DBSCANCluster> listDBSCANClusters = null;

    private DataContainer() {}
    public static DataContainer getInstance() {
        return dataContainer;
    }

    public List<LatLng> getListCrushesByYear(int year, Context context) throws JSONException {
        if(!crushesMap.containsKey(year)) {
            List<LatLng> list = DataProvider.getInstance().readListCrushesByYear(year, context);
            crushesMap.put(year, list);
        }
        return crushesMap.get(year);
    }

    public List<DBSCANCluster> getListDBSCANClusters(Context context) throws JSONException {
        if(listDBSCANClusters == null) {
            listDBSCANClusters = DataProvider.getInstance().readListDBSCANClusters(Const.DBSCAN_CLUSTERS_SRC, context);
        }
        return listDBSCANClusters;
    }

}
