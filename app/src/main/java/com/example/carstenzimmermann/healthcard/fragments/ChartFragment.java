package com.example.carstenzimmermann.healthcard.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carstenzimmermann.healthcard.DayAxisValueFormatter;
import com.example.carstenzimmermann.healthcard.R;
import com.example.carstenzimmermann.healthcard.entities.Measurement;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by Carsten Zimmermann on 24.11.2016.
 */

public class ChartFragment extends Fragment
{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.chart_mp_chart, container, false);
        LineChart chart = (LineChart) view.findViewById(R.id.mp_chart);

        /*
        List<Entry> entries = new ArrayList<Entry>();
        entries.add(new Entry(1f, 1f));
        entries.add(new Entry(2f, 4f));
        entries.add(new Entry(10f, 6f));

        LineDataSet lineDataSet = new LineDataSet(entries, "Label");
        lineDataSet.setColor(Color.BLUE);

        LineData lineData = new LineData(lineDataSet);
        chart.setData(lineData);
        chart.setPinchZoom(true);
        XAxis xAxis = chart.getXAxis();
        //todo: Add a formater formating the xAxis labels properly
//        xAxis.setValueFormatter(new DayAxisValueFormatter());
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.invalidate();
        */

        return view;
    }

    public void loadData(List<Measurement> measurementsList)
    {
        LineChart chart = (LineChart) getView().findViewById(R.id.mp_chart);
        List<Entry> entries = new ArrayList<Entry>();
        for (Measurement measurement:measurementsList)
        {
            // convert the date values into a time stamp:
            Calendar cal = Calendar.getInstance();
            cal.set(measurement.getYear(), measurement.getMonth(), measurement.getDayOfMonth());

            // build the new entry
            Entry entry = new Entry();
            entry.setX(cal.getTimeInMillis());
            entry.setY(measurement.getWeight());
            entries.add(entry);
        }

        LineDataSet lineDataSet = new LineDataSet(entries, "Label");
        lineDataSet.setColor(Color.BLUE);

        LineData lineData = new LineData(lineDataSet);
        chart.setData(lineData);
        chart.setPinchZoom(true);
        XAxis xAxis = chart.getXAxis();
        //todo: Add a formater formating the xAxis labels properly
//        xAxis.setValueFormatter(new DayAxisValueFormatter());
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.invalidate();
    }
}
