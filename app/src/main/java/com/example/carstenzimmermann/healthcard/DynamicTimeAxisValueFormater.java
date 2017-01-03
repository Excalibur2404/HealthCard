package com.example.carstenzimmermann.healthcard;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * Created by Carsten Zimmermann on 03.01.2017.
 */

public class DynamicTimeAxisValueFormater implements IAxisValueFormatter
{
    //todo: remove this class if not needed
    @Override
    public String getFormattedValue(float value, AxisBase axis)
    {
        float week = value / 604800000;
        int i = Math.round(week);
        return "W " + i;
    }
}
