package com.example.carstenzimmermann.healthcard.entities;

import android.util.Log;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Carsten Zimmermann on 22.10.2016.
 */

public class Measurement
{
    private Float weight;
    private Float height;
    private int dayOfMonth;
    private int month;
    private int year;
    private int _id;
    private int childId;

    public Measurement(){}

    public Measurement(int id, int childId, int dayOfMonth, int month, int year, float weight, float height)
    {
        this._id = id;
        this.childId = childId;
        this.dayOfMonth = dayOfMonth;
        this.month = month;
        this.year = year;
        this.weight = weight;
        this.height = height;
    }

    public Measurement(int id, int childId, int dayOfMonth, int month, int year, Float weight, Float height)
    {
        this._id = id;
        this.childId = childId;
        this.dayOfMonth = dayOfMonth;
        this.month = month;
        this.year = year;
        this.weight = weight;
        this.height = height;
    }

    public Float getWeight()
    {
        return weight;
    }

    public void setWeight(Float weight)
    {
        this.weight = weight;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public int getDayOfMonth()
    {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth)
    {
        this.dayOfMonth = dayOfMonth;
    }

    public int getMonth()
    {
        return month;
    }

    public void setMonth(int month)
    {
        this.month = month;
    }

    public int getYear()
    {
        return year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    public int get_id()
    {
        return _id;
    }

    public void set_id(int _id)
    {
        this._id = _id;
    }

    public int getChildId()
    {
        return childId;
    }

    public void setChildId(int childId)
    {
        this.childId = childId;
    }

    public float getBMI()
    {
        return getBMI(weight, height);
    }
    public static float getBMI(float weight, float height)
    {
        float bmi =  weight / (height / 100 * height / 100);
        Log.d(Measurement.class.getName(), "BMI is " + bmi);
        float roundedBmiTemp = Math.round(bmi * 10);
        Log.d(Measurement.class.getName(), "roundedBmiTemp is " + roundedBmiTemp);
        float roundedBmi = roundedBmiTemp / 10;
        Log.d(Measurement.class.getName(), "roundedBmi is " + roundedBmi);
        return roundedBmi;
    }

    public static String formatHeight(float height)
    {
        NumberFormat format = DecimalFormat.getInstance(Locale.getDefault());
        format.setMaximumFractionDigits(0);
        return format.format(height);
    }

    public static String formatWeight(float weight)
    {
        NumberFormat format = DecimalFormat.getInstance(Locale.getDefault());
        format.setMaximumFractionDigits(3);
        return format.format(weight);
    }

    public static String formatBMI(float bmi)
    {
        NumberFormat format = DecimalFormat.getInstance(Locale.getDefault());
        format.setMaximumFractionDigits(1);
        return format.format(bmi);
    }
}