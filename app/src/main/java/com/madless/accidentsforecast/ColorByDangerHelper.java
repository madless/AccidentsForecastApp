package com.madless.accidentsforecast;

import android.graphics.Color;

import com.madless.accidentsforecast.model.UkraineDistrict;

import java.util.List;

/**
 * Created by User on 10.06.2017.
 */

public class ColorByDangerHelper {
    public static List<UkraineDistrict> setupColorByDanger(List<UkraineDistrict> districts) {
        int maxDanger = districts.get(0).getDanger();
        int minDanger = districts.get(0).getDanger();
        for (UkraineDistrict d: districts) {
            if(d.getDanger() > maxDanger) {
                maxDanger = d.getDanger();
            }
            if(d.getDanger() < minDanger) {
                minDanger = d.getDanger();
            }
        }
        int amountOfDangerLevels = maxDanger - minDanger + 1;

        int maxColorValue = 255;
        int minColorValue = 0;
        int step = maxColorValue / amountOfDangerLevels;

        for (UkraineDistrict d: districts) {
            int red = minColorValue + step * d.getDanger();
            int green = maxColorValue - step * d.getDanger();
            int color = Color.rgb(red, green, 0);
            d.setColor(color);
        }

        return districts;
    }
}
