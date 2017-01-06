package com.example.carstenzimmermann.healthcard.entities;

/**
 * Created by Carsten Zimmermann on 02.01.2017.
 */

public class PercentileValue
{
    public static final int WEEKS = 1;
    public static final int MONTHS = 2;

    private int distance;
    private int distanceUnit;
    private float weight3rd;
    private float weight15th;
    private float weightMed;
    private float weight85th;
    private float weight97th;
    private float height3rd;
    private float height15th;
    private float heightMed;
    private float height85th;
    private float height97th;
    private float bmi3rd;
    private float bmi15th;
    private float bmiMed;
    private float bmi85th;
    private float bmi97th;

    public PercentileValue(){}

    public PercentileValue(int distance,
                    int distanceUnit,
                    float weight3rd,
                    float weight15th,
                    float weightMed,
                    float weight85th,
                    float weight97th,
                    float height3rd,
                    float height15th,
                    float heightMed,
                    float height85th,
                    float height97th,
                    float bmi3rd,
                    float bmi15th,
                    float bmiMed,
                    float bmi85th,
                    float bmi97th)
    {
        this.distance = distance;
        this.distanceUnit = distanceUnit;

        this.weight3rd = weight3rd;
        this.weight15th = weight15th;
        this.weightMed = weightMed;
        this.weight85th = weight85th;
        this.weight97th = weight97th;

        this.height3rd = height3rd / 100;
        this.height15th = height15th / 100;
        this.heightMed = heightMed / 100;
        this.height85th = height85th / 100;
        this.height97th = height97th / 100;

        this.bmi3rd = bmi3rd;
        this.bmi15th = bmi15th;
        this.bmiMed = bmiMed;
        this.bmi85th = bmi85th;
        this.bmi97th = bmi97th;
    }

    public float getBmi15th()
    {
        return bmi15th;
    }

    public void setBmi15th(float bmi15th)
    {
        this.bmi15th = bmi15th;
    }

    public float getBmi3rd()
    {
        return bmi3rd;
    }

    public void setBmi3rd(float bmi3rd)
    {
        this.bmi3rd = bmi3rd;
    }

    public float getBmi85th()
    {
        return bmi85th;
    }

    public void setBmi85th(float bmi85th)
    {
        this.bmi85th = bmi85th;
    }

    public float getBmi97th()
    {
        return bmi97th;
    }

    public void setBmi97th(float bmi97th)
    {
        this.bmi97th = bmi97th;
    }

    public float getBmiMed()
    {
        return bmiMed;
    }

    public void setBmiMed(float bmiMed)
    {
        this.bmiMed = bmiMed;
    }

    public int getDistance()
    {
        return distance;
    }

    public void setDistance(int distance)
    {
        this.distance = distance;
    }

    public int getDistanceUnit()
    {
        return distanceUnit;
    }

    public void setDistanceUnit(int distanceUnit)
    {
        this.distanceUnit = distanceUnit;
    }

    public float getHeight15th()
    {
        return height15th;
    }

    public void setHeight15th(float height15th)
    {
        this.height15th = height15th;
    }

    public float getHeight3rd()
    {
        return height3rd;
    }

    public void setHeight3rd(float height3rd)
    {
        this.height3rd = height3rd;
    }

    public float getHeight85th()
    {
        return height85th;
    }

    public void setHeight85th(float height85th)
    {
        this.height85th = height85th;
    }

    public float getHeight97th()
    {
        return height97th;
    }

    public void setHeight97th(float height97th)
    {
        this.height97th = height97th;
    }

    public float getHeightMed()
    {
        return heightMed;
    }

    public void setHeightMed(float heightMed)
    {
        this.heightMed = heightMed;
    }

    public float getWeight15th()
    {
        return weight15th;
    }

    public void setWeight15th(float weight15th)
    {
        this.weight15th = weight15th;
    }

    public float getWeight3rd()
    {
        return weight3rd;
    }

    public void setWeight3rd(float weight3rd)
    {
        this.weight3rd = weight3rd;
    }

    public float getWeight85th()
    {
        return weight85th;
    }

    public void setWeight85th(float weight85th)
    {
        this.weight85th = weight85th;
    }

    public float getWeight97th()
    {
        return weight97th;
    }

    public void setWeight97th(float weight97th)
    {
        this.weight97th = weight97th;
    }

    public float getWeightMed()
    {
        return weightMed;
    }

    public void setWeightMed(float weightMed)
    {
        this.weightMed = weightMed;
    }
}
