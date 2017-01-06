package com.example.carstenzimmermann.healthcard.entities;

import android.icu.util.Measure;

/**
 * Created by Carsten Zimmermann on 22.10.2016.
 */

public class Measurement
{
    private float weight;
    private float hight;
    private int dayOfMonth;
    private int month;
    private int year;
    private int _id;
    private int childId;

    public Measurement(){}

    public Measurement(int id, int childId, int dayOfMonth, int month, int year, float weight, float hight)
    {
        this._id = id;
        this.childId = childId;
        this.dayOfMonth = dayOfMonth;
        this.month = month;
        this.year = year;
        this.weight = weight;
        this.hight = hight;
    }

    public float getWeight()
    {
        return weight;
    }

    public void setWeight(float weight)
    {
        this.weight = weight;
    }

    public float getHight() {
        return hight;
    }

    public void setHight(float hight) {
        this.hight = hight;
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
        return getWeight() * getHight() * getHight();
    }
}