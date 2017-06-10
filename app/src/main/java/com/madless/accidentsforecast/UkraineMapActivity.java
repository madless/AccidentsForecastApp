package com.madless.accidentsforecast;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.madless.accidentsforecast.model.UkraineDistrict;
import com.madless.accidentsforecast.utils.Const;
import com.madless.accidentsforecast.utils.Loggr;

import java.util.List;

/**
 * Created by User on 10.06.2017.
 */

public class UkraineMapActivity extends BaseActivity implements OnMapReadyCallback {

    private Loggr loggr = new Loggr(this.getClass());
    private GoogleMap map;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng startPoint = new LatLng(Const.START_POINT_LAT_UA, Const.START_POINT_LNG_UA);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(startPoint, Const.START_ZOOM));

        List<UkraineDistrict> districts = RepositoryManager.get().getDistricts();
        ColorByDangerHelper.setupColorByDanger(districts);

        for (UkraineDistrict d: districts) {
            BitmapDescriptor bitmap = BitmapDescriptorFactory.fromBitmap(createIcon(d));
            MarkerOptions options = new MarkerOptions()
                    .position(new LatLng(d.getLatitude(), d.getLonitude()))
                    .icon(bitmap);
            map.addMarker(options);
        }
    }

    public static Bitmap createIcon(UkraineDistrict d) {
        Bitmap b = Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        paint.setColor(d.getColor());
        Canvas c = new Canvas(b);
        c.drawCircle(25, 25, 25, paint);
        return b;
    }
}
