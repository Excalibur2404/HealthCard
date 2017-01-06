package com.example.carstenzimmermann.healthcard.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carstenzimmermann.healthcard.DataManager;
import com.example.carstenzimmermann.healthcard.R;
import com.example.carstenzimmermann.healthcard.entities.Measurement;
import com.example.carstenzimmermann.healthcard.entities.PercentileValue;
import com.example.carstenzimmermann.healthcard.entities.PercentilesRepository;
import com.github.mikephil.charting.charts.LineChart;
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
    public static final String KEY_GRAPH_LABEL = "graph_label";
    public static final String KEY_SEX = "sex";
    public static final String KEY_BIRTHDATE_DAY_OF_MONTH = "birthdate_day_of_month";
    public static final String KEY_BIRTHDATE_MONTH = "birthdate_month";
    public static final String KEY_BIRTHDATE_YEAR = "birthdate_year";
    public static final String KEY_CHILD_ID = "child_id";

    private LineChart chart;
    private String graphLabel;
    private int sex;
    private int birthdateDayOfMonth;
    private int birthdateMonth;
    private int birthdateYear;
    private int childId;
    private DataManager dataManager;
    private List<Measurement> measurementsList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        Log.d(this.getClass().getName(), "Executing onCreate()...");
        super.onCreate(savedInstanceState);
        dataManager = DataManager.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        Log.d(this.getClass().getName(), "Executing onCreateView()...");
        View view = inflater.inflate(R.layout.chart_mp_chart, container, false);
        chart = (LineChart) view.findViewById(R.id.mp_chart);

        // get the criteria for loading the data from the bundle
        Bundle bundle = this.getArguments();
        graphLabel = bundle.getString(KEY_GRAPH_LABEL);
        sex = bundle.getInt(KEY_SEX);
        birthdateDayOfMonth = bundle.getInt(KEY_BIRTHDATE_DAY_OF_MONTH);
        birthdateMonth = bundle.getInt(KEY_BIRTHDATE_MONTH);
        birthdateYear = bundle.getInt(KEY_BIRTHDATE_YEAR);
        childId = bundle.getInt(KEY_CHILD_ID);

        //load the data:
        measurementsList = dataManager.getMeasurements(childId);
        loadData(measurementsList, graphLabel, sex, birthdateDayOfMonth, birthdateMonth, birthdateYear);
        return view;
    }

    private void loadData(List<Measurement> measurementsList,
                         String graphLabel,
                         int sex,
                         int birthdateDayOfMonth,
                         int birthdateMonth,
                         int birthdateYear)
    {
        Log.d(this.getClass().getName(), "Executing loadData()...");
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
            Log.d(this.getClass().getName(), "OffsetTimestamp is " + offsetTimestamp);

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
        lineData3Percentile.setColor(Color.rgb(255, 0, 0), 60);
        lineData3Percentile.setDrawValues(false);
        lineData3Percentile.setDrawCircles(false);
        lineData3Percentile.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lines.add(lineData3Percentile);

        lineData15Percentile = new LineDataSet(percentile15entries, getString(R.string.percentile15));
        lineData15Percentile.setColor(Color.rgb(255, 114, 0), 60);
        lineData15Percentile.setDrawValues(false);
        lineData15Percentile.setDrawCircles(false);
        lineData15Percentile.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lines.add(lineData15Percentile);

        lineDataMedian = new LineDataSet(medianEntries, getString(R.string.median));
        lineDataMedian.setColor(Color.rgb(0, 175, 0), 60);
        lineDataMedian.setDrawValues(false);
        lineDataMedian.setDrawCircles(false);
        lineDataMedian.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lines.add(lineDataMedian);

        lineData85Percentile = new LineDataSet(percentile85entries, getString(R.string.percentile85));
        lineData85Percentile.setColor(Color.rgb(255, 114, 0), 60);
        lineData85Percentile.setDrawValues(false);
        lineData85Percentile.setDrawCircles(false);
        lineData85Percentile.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lines.add(lineData85Percentile);

        lineData97Percentile = new LineDataSet(percentile97entries, getString(R.string.percentile97));
        lineData97Percentile.setColor(Color.rgb(255, 0, 0), 60);
        lineData97Percentile.setDrawValues(false);
        lineData97Percentile.setDrawCircles(false);
        lineData97Percentile.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lines.add(lineData97Percentile);

        float xMaxValue = 0f;
        float yMaxValue = 0f;

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
            xMaxValue = entry.getX();
            yMaxValue = entry.getY();
            entries.add(entry);
        }

        LineDataSet lineDataSet = new LineDataSet(entries, graphLabel);
        lineDataSet.setColor(Color.BLUE);
        lineDataSet.setCircleColor(Color.BLUE);
        lineDataSet.setCircleColorHole(Color.BLUE);
        lineDataSet.setCircleRadius(2f);
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lines.add(lineDataSet);

        LineData lineData = new LineData(lines);
        chart.setData(lineData);
        chart.setPinchZoom(true);
        XAxis xAxis = chart.getXAxis();
        xAxis.setDrawGridLines(false);

        xAxis.setDrawLimitLinesBehindData(true);
        xAxis.setDrawLabels(false);
        drawLimitLines(xAxis, birthdateTimestamp);
        chart.getAxisRight().setEnabled(false);
        chart.getLegend().setEnabled(false);
        chart.setDescription(null);

        // Zoomfaktorberechnung für Y-Achse:
        // 1. Suche den zeitlich letzten Y-Wert der Kindsdaten. Suche bis zu diesem Zeitpunkt nach dem maximalen Wert in der
        //    Messwertdatenkurze und dem 97%-Perzentil und speichere den gefundenen Y-Wert als "yCurrent".
        // 2. Suche den maximalen Y-Wert in der Kindsdatenkurve und dem 97% Perzentil über alle X-Werte und speichere ihn als "yMax".
        // 3. Der Faktor ist gleich yMax / yCurrent.

        // Zoomfaktorberechnung für X-Achse:
        // 1. Suche den zeitlich letzten Y-Wert des Kindes und speichere dessen X-Wert als "xCurrent".
        // 2. Suche den zeitlich letzten X-Wert über alle Daten und speichere ihn unter "xMax".
        // 4. Der Faktor ist gleich xMax / xCurrent.


        //determine the zoom factor:
        float zoomFactorX = getZoomFactorX(percentile97entries, entries);
        float zoomFactorY = getZoomFactorY(percentile97entries, entries);
        chart.zoom(zoomFactorX, zoomFactorY, 0f, 0f);
        //todo: set a proper initial zoom
        /*float xZoomFactor = 157852800000f / xMaxValue;
        float yZoomFactor = 23.8f / 5.6f;
        chart.zoom(xZoomFactor, yZoomFactor, 0f, 0f);*/

        // update rhe chart
        chart.invalidate();
    }

    private float getZoomFactorX(List<Entry> percentil97Entries, List<Entry> measurementEntries)
    {
        // 1. Suche den zeitlich letzten Y-Wert des Kindes und speichere dessen X-Wert als "xCurrent".
        float xCurrent = measurementEntries.get(measurementEntries.size()-1).getX();

        // 2. Suche den zeitlich letzten X-Wert über alle Daten und speichere ihn unter "xMax".
        float temp1 = measurementEntries.get(measurementEntries.size()-1).getX();
        float temp2 = percentil97Entries.get(percentil97Entries.size()-1).getX();
        float xMax = temp1 > temp2 ? temp1 : temp2;

        // 3. Der Faktor ist gleich xMax / xCurrent.
        return xMax / xCurrent;
    }

    private float getZoomFactorY(List<Entry> percentil97Entries, List<Entry> measurementEntries)
    {
        // 1. Suche den zeitlich letzten Y-Wert der Kindsdaten.
        float xMaxMeasurements = measurementEntries.get(measurementEntries.size()-1).getX();

        // 2. Suche bis zu diesem Zeitpunkt nach dem maximalen Wert in der
        //    Messwertdatenkurve und dem 97%-Perzentil und speichere den gefundenen Y-Wert als "yCurrent".
        float yCurrent = 0f;
        for (Entry entry:percentil97Entries)
        {
            if (entry.getX() <= xMaxMeasurements)
            {
                if (entry.getY() > yCurrent) yCurrent = entry.getY();
            }
            else break;
        }
        for (Entry entry:measurementEntries)
        {
            if (entry.getX() <= xMaxMeasurements)
            {
                if (entry.getY() > yCurrent) yCurrent = entry.getY();
            }
            else break;
        }

        // 3. Suche den maximalen Y-Wert in der Kindsdatenkurve und dem 97% Perzentil über alle X-Werte und speichere ihn als "yMax".
        float yMax = 0f;
        for (Entry entry:percentil97Entries)
        {
            if (entry.getY() > yMax) yMax = entry.getY();
        }
        for (Entry entry:measurementEntries)
        {
            if (entry.getY() > yMax) yMax = entry.getY();
        }

        // 5. Der Faktor ist gleich yMax / yCurrent.
        return yMax / yCurrent;
    }

    private void drawLimitLines(XAxis xAxis, long baseDateTimestamp)
    {
        Log.d(this.getClass().getName(), "Executing drawLimitLines()...");
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
