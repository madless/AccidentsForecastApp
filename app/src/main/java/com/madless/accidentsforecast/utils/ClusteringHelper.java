package com.madless.accidentsforecast.utils;

import android.content.Context;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.maps.android.clustering.ClusterManager;
import com.madless.accidentsforecast.DataContainer;

import org.json.JSONException;

import java.util.List;

/**
 * Created by User on 17.05.2017.
 */

@Deprecated
public class ClusteringHelper {

    public static void showClusters(GoogleMap map, Context activity) {
        ClusterManager<DBSCANCluster> clusterManager = new ClusterManager<DBSCANCluster>(activity, map);
        map.setOnCameraIdleListener(clusterManager);

        try {
            List<DBSCANCluster> dbscanClusters = DataContainer.getInstance().getListDBSCANClusters(activity);
            clusterManager.addItems(dbscanClusters);
        } catch (JSONException e) {
            Toast.makeText(activity, "Problem reading list of clusters.", Toast.LENGTH_LONG).show();
        }
    }
}
