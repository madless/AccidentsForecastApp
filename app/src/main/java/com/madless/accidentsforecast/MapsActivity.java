package com.madless.accidentsforecast;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.clustering.ClusterManager;
import com.madless.accidentsforecast.model.Cluster;
import com.madless.accidentsforecast.utils.Const;
import com.madless.accidentsforecast.utils.HeatMapHelper;
import com.madless.accidentsforecast.utils.IntentHelper;
import com.madless.accidentsforecast.utils.Loggr;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private Loggr loggr = new Loggr(this.getClass());
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng startPoint = new LatLng(Const.START_POINT_LAT, Const.START_POINT_LNG);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(startPoint, Const.START_ZOOM));

        List<LatLng> crushes2015 = RepositoryManager.get().getPoints();
        HeatMapHelper heatMapHelper = new HeatMapHelper();
        heatMapHelper.addHeatMap(crushes2015, map);

        List<Cluster> clusters = RepositoryManager.get().getClusters();
        loggr.d("Clusters: " + clusters);
        ClusterManager<Cluster> clusterManager = new ClusterManager<>(MapsActivity.this, map);
        AccidentsClusterIconRenderer renderer = new AccidentsClusterIconRenderer(this, map, clusterManager);
        clusterManager.setRenderer(renderer);
        map.setOnCameraIdleListener(clusterManager);
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                loggr.d("On click: " + marker.getTitle());
                Toast.makeText(MapsActivity.this, "click: " + marker.getTitle(), Toast.LENGTH_SHORT).show();
                IntentHelper.startClusterActivity(MapsActivity.this, Integer.valueOf(marker.getTitle()));
                return false;
            }
        });
        clusterManager.addItems(clusters);
//        ClusteringHelper.showClusters(map, this);
    }
}
