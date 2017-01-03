package com.example.carstenzimmermann.healthcard.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carstenzimmermann.healthcard.DynamicTimeAxisValueFormater;
import com.example.carstenzimmermann.healthcard.R;
import com.example.carstenzimmermann.healthcard.entities.Measurement;
import com.example.carstenzimmermann.healthcard.entities.PercentileValue;
import com.example.carstenzimmermann.healthcard.entities.PercentilesRepository;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new DynamicTimeAxisValueFormater());
        return view;
    }

    public void loadData(List<Measurement> measurementsList,
                         String graphLabel,
                         int sex,
                         int birthdateDayOfMonth,
                         int birthdateMonth,
                         int birthdateYear)
    {
        LineChart chart = (LineChart) getView().findViewById(R.id.mp_chart); //the chart object containing all lines
        List<ILineDataSet> lines = new ArrayList<ILineDataSet>(); // a list containing all the lines. This is handed over to the chart object
        LineDataSet lineData3Percentile = null; // an object grouping all values belonging to the 3rd percentile
        LineDataSet lineData15Percentile = null; // an object grouping all values belonging to the 15th percentile
        LineDataSet lineDataMedian = null; // an object grouping all values belonging to the median
        LineDataSet lineData85Percentile = null; // an object grouping all values belonging to the 85th percentile
        LineDataSet lineData97Percentile = null; // an object grouping all values belonging to the 97th percentile
        List<Entry> percentile3entries = new ArrayList<Entry>(); // a list of values belonging to the 3rd percentile
        List<Entry> percentile15entries = new ArrayList<Entry>(); // a list of values belonging to the 15th percentile
        List<Entry> medianEntries = new ArrayList<Entry>(); // a list of values belonging to the median
        List<Entry> percentile85entries = new ArrayList<Entry>(); // a list of values belonging to the 85th percentile
        List<Entry> percentile97entries = new ArrayList<Entry>(); // a list of values belonging to the 97th percentile

        //
        long birthdateTimestamp; // The birthdate serves as the 0 point for the chart. It is deducted from all further timestamps.
        long offsetTimestamp; // The offset is the number of milliseconds each measurement value is off from the birthdate.

        List<PercentileValue> percentileValueList = PercentilesRepository.getPercentileData(sex);

        Entry entry = null;
        Calendar cal = Calendar.getInstance();
        cal.set(birthdateYear, birthdateMonth, birthdateDayOfMonth);
        birthdateTimestamp = cal.getTimeInMillis();

        for (PercentileValue percentileValue:percentileValueList)
        {
            // convert the date values into a time stamp:
            cal.set(birthdateYear, birthdateMonth, birthdateDayOfMonth);
            if (percentileValue.getDistanceUnit() == PercentileValue.WEEKS)
            {
                cal.add(Calendar.DAY_OF_MONTH, 7*percentileValue.getDistance());
            }
            else
            {
                cal.add(Calendar.MONTH, percentileValue.getDistance());
            }
            offsetTimestamp = cal.getTimeInMillis() - birthdateTimestamp;

            entry = new Entry();
            entry.setX(offsetTimestamp);
            entry.setY(percentileValue.getWeight3rd());
            percentile3entries.add(entry);

            entry = new Entry();
            entry.setX(offsetTimestamp);
            entry.setY(percentileValue.getWeight15th());
            percentile15entries.add(entry);

            entry = new Entry();
            entry.setX(offsetTimestamp);
            entry.setY(percentileValue.getWeightMed());
            medianEntries.add(entry);

            entry = new Entry();
            entry.setX(offsetTimestamp);
            entry.setY(percentileValue.getWeight85th());
            percentile85entries.add(entry);

            entry = new Entry();
            entry.setX(offsetTimestamp);
            entry.setY(percentileValue.getWeight97th());
            percentile97entries.add(entry);
        }

        lineData3Percentile = new LineDataSet(percentile3entries, getString(R.string.percentile3));
        lineData3Percentile.setColor(Color.rgb(255, 0, 0));
        lineData3Percentile.setDrawValues(false);
        lineData3Percentile.setDrawCircles(false);
        lineData3Percentile.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lines.add(lineData3Percentile);

        lineData15Percentile = new LineDataSet(percentile15entries, getString(R.string.percentile15));
        lineData15Percentile.setColor(Color.rgb(255, 114, 0));
        lineData15Percentile.setDrawValues(false);
        lineData15Percentile.setDrawCircles(false);
        lineData15Percentile.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lines.add(lineData15Percentile);

        lineDataMedian = new LineDataSet(medianEntries, getString(R.string.median));
        lineDataMedian.setColor(Color.rgb(0, 175, 0));
        lineDataMedian.setDrawValues(false);
        lineDataMedian.setDrawCircles(false);
        lineDataMedian.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lines.add(lineDataMedian);

        lineData85Percentile = new LineDataSet(percentile85entries, getString(R.string.percentile85));
        lineData85Percentile.setColor(Color.rgb(255, 114, 0));
        lineData85Percentile.setDrawValues(false);
        lineData85Percentile.setDrawCircles(false);
        lineData85Percentile.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lines.add(lineData85Percentile);

        lineData97Percentile = new LineDataSet(percentile97entries, getString(R.string.percentile97));
        lineData97Percentile.setColor(Color.rgb(255, 0, 0));
        lineData97Percentile.setDrawValues(false);
        lineData97Percentile.setDrawCircles(false);
        lineData97Percentile.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lines.add(lineData97Percentile);

        // draw the line of the child
        List<Entry> entries = new ArrayList<Entry>();
        for (Measurement measurement:measurementsList)
        {
            // convert the date values into a time stamp:
            cal = Calendar.getInstance();
            cal.set(measurement.getYear(), measurement.getMonth(), measurement.getDayOfMonth());

            // build the new entry
            entry = new Entry();
            entry.setX(cal.getTimeInMillis() - birthdateTimestamp);
            Log.d(this.getClass().getName(), "X-Value is " + entry.getX());
            entry.setY(measurement.getWeight());
            entries.add(entry);
        }

        LineDataSet lineDataSet = new LineDataSet(entries, graphLabel);
        lineDataSet.setColor(Color.BLUE);
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lines.add(lineDataSet);

        LineData lineData = new LineData(lines);
        chart.setData(lineData);
        chart.setPinchZoom(true);
        XAxis xAxis = chart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLimitLinesBehindData(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(604800000f);
        xAxis.setGranularityEnabled(true);
        xAxis.setDrawLabels(false);
        drawLimitLines(xAxis, birthdateTimestamp);
        chart.getAxisRight().setEnabled(false);
        chart.getLegend().setEnabled(false);
        chart.setDescription(null);
        chart.invalidate();
    }

    private void drawLimitLines(XAxis xAxis, long baseDateTimestamp)
    {
        int textColor = Color.rgb(80, 80, 80);
        int lineColor = Color.rgb(200, 200, 200);
        float textSize = 9f;
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(baseDateTimestamp);

        for (int i=1; i<11; i++)
        {
            cal.add(Calendar.MONTH, 1);
            long baseDatePlusOneYear = cal.getTimeInMillis();
            long limit = baseDatePlusOneYear - baseDateTimestamp;
            LimitLine l = new LimitLine(limit);
            l.setLabelPosition(LimitLine.LimitLabelPosition.LEFT_BOTTOM);
            l.setLineColor(lineColor);
            l.setTextSize(textSize);
            l.setTextColor(textColor);
            l.setLabel(getString(R.string.month) + " " + i);
            xAxis.addLimitLine(l);
        }

        cal.setTimeInMillis(baseDateTimestamp);
        for (int i=1; i<5; i++)
        {
            cal.add(Calendar.YEAR, 1);
            long baseDatePlusOneYear = cal.getTimeInMillis();
            long limit = baseDatePlusOneYear - baseDateTimestamp;
            LimitLine l = new LimitLine(limit);
            l.setLabelPosition(LimitLine.LimitLabelPosition.LEFT_BOTTOM);
            l.setLineColor(lineColor);
            l.setTextSize(textSize);
            l.setLabel(getString(R.string.year) + " " + i);
            xAxis.addLimitLine(l);
        }
    }
}
