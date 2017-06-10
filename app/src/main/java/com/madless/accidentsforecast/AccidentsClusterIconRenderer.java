package com.madless.accidentsforecast;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.madless.accidentsforecast.model.Cluster;

import static android.graphics.Paint.ANTI_ALIAS_FLAG;

/**
 * Created by User on 05.06.2017.
 */

public class AccidentsClusterIconRenderer extends DefaultClusterRenderer<Cluster> {

    private Context context;

    public AccidentsClusterIconRenderer(Context context, GoogleMap map, ClusterManager<Cluster> clusterManager) {
        super(context, map, clusterManager);
        this.context = context;
    }

    @Override
    protected void onBeforeClusterItemRendered(Cluster item, MarkerOptions markerOptions) {
//        markerOptions.icon(item.getIcon());
//        markerOptions.snippet(item.getSnippet());
        Bitmap bitmap = textAsBitmap(String.valueOf(item.getNum()), 50);
        markerOptions.title(String.valueOf(item.getId()));
        markerOptions.snippet(String.valueOf(item.getNum()));
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(bitmap));
        super.onBeforeClusterItemRendered(item, markerOptions);
    }

    public Bitmap textAsBitmap(String text, float textSize) {
        Paint paint = new Paint(ANTI_ALIAS_FLAG);
        paint.setTextSize(textSize);
        paint.setColor(Color.RED);
        paint.setTextAlign(Paint.Align.LEFT);
        float baseline = -paint.ascent(); // ascent() is negative
        int width = (int) (paint.measureText(text) + 0.5f); // round
        int height = (int) (baseline + paint.descent() + 0.5f);
        int maxSize = width > height ? width: height;
        Bitmap image = Bitmap.createBitmap(maxSize, maxSize, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(image);
        canvas.drawCircle(maxSize / 2, maxSize / 2, maxSize / 2, paint);
        paint.setColor(Color.WHITE);
        canvas.drawText(text, 0, maxSize / 2 + height / 4, paint);
        return image;
    }
}
