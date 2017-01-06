package com.example.carstenzimmermann.healthcard.entities;

/**
 * Created by Carsten Zimmermann on 22.10.2016.
 */

public class Measurement
{
    private float weight;
    private float height;
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

    public float getWeight()
    {
        return weight;
    }

    public void setWeight(float weight)
    {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
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
        return Math.round(weight / (height * height) * 100) / 100;
    }
}