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
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
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
//        View view = inflater.inflate(R.layout.chart, container, false);
        /*LineChartView chart = (LineChartView) view.findViewById(R.id.chart);
        chart.setZoomEnabled(true);
        chart.setZoomType(ZoomType.HORIZONTAL_AND_VERTICAL);

        List<PointValue> values = new ArrayList<PointValue>();
        values.add(new PointValue(0, 2));
        values.add(new PointValue(1, 4));
        values.add(new PointValue(2, 3));
        values.add(new PointValue(3, 4));

        //In most cased you can call data model methods in builder-pattern-like manner.
        Line line = new Line(values).setColor(Color.BLUE).setCubic(true).setHasLabels(true);
        List<Line> lines = new ArrayList<Line>();
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);

        chart.setLineChartData(data);

        //add x-axis
        Axis xAxis = new Axis();
        xAxis.setName(getString(R.string.weight));
        xAxis.setHasLines(true);
        xAxis.setLineColor(Color.BLACK);
        xAxis.setTextColor(Color.BLACK);
        chart.getChartData().setAxisXBottom(xAxis);

        // add y-axis
        Axis yAxis = new Axis();
        yAxis.setName(getString(R.string.weight));
        yAxis.setHasLines(true);
        yAxis.setTextColor(Color.BLACK);
        yAxis.setLineColor(Color.BLACK);
        chart.getChartData().setAxisYLeft(yAxis);*/

        View view = inflater.inflate(R.layout.chart_mp_chart, container, false);
        LineChart chart = (LineChart) view.findViewById(R.id.mp_chart);

        List<Entry> entries = new ArrayList<Entry>();
        entries.add(new Entry(1f, 1f));
        entries.add(new Entry(2f, 4f));
        entries.add(new Entry(3f, 6f));

        LineDataSet lineDataSet = new LineDataSet(entries, "Label");
        lineDataSet.setColor(Color.BLUE);

        LineData lineData = new LineData(lineDataSet);
        chart.setData(lineData);
        chart.setPinchZoom(true);
        XAxis xAxis = chart.getXAxis();
//        xAxis.setValueFormatter(new DayAxisValueFormatter());
        //TODO: Try to format the x-axis values to show points in time
        chart.invalidate();

        return view;
    }

    public void loadData(LineChartData data)
    {
//        LineChartView chart = (LineChartView) getView().findViewById(R.id.chart);
//        chart.setZoomEnabled(true);
//        chart.setZoomType(ZoomType.HORIZONTAL_AND_VERTICAL);
//
//        chart.setLineChartData(data);
//
//        //add x-axis
//        Axis xAxis = new Axis();
//        xAxis.setName("Datensatznummer");
//        xAxis.setHasLines(true);
//        xAxis.setLineColor(Color.BLACK);
//        xAxis.setTextColor(Color.BLACK);
//        chart.getChartData().setAxisXBottom(xAxis);
//
//        // add y-axis
//        Axis yAxis = new Axis();
//        yAxis.setName(getString(R.string.weight));
//        yAxis.setHasLines(true);
//        yAxis.setTextColor(Color.BLACK);
//        yAxis.setLineColor(Color.BLACK);
//        chart.getChartData().setAxisYLeft(yAxis);

        //chart.setZoomLevel(2f, 3f, 2f);
    }
}
