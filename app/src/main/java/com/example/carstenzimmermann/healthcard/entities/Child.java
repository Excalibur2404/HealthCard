package com.example.carstenzimmermann.healthcard.entities;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Carsten Zimmermann on 22.10.2016.
 */


public class Child
{
    private int _id;
    private String firstName;
    private String lastName;
    private int birthdateDayOfMonth;
    private int birthdateMonth;
    private int birthdateYear;
    private int sex;
    private Bitmap portrait;
    private ArrayList<Measurement> measurements;

    public static String KEY_ID = "child_id";
    public static String KEY_FIRST_NAME = "child_first_name";
    public static String KEY_LAST_NAME = "child_last_name";
    public static String KEY_BIRTHDATE_DAY_OF_MONTH = "child_birthdate_day_of_month";
    public static String KEY_BIRTHDATE_MONTH = "child_birthdate_month";
    public static String KEY_BIRTHDATE_YEAR = "child_birthdate_year";
    public static String KEY_SEX = "child_key_sex";
    public static String KEY_PORTRAIT = "child_portrait";

    public ArrayList<Measurement> getMeasurements()
    {
        return measurements;
    }

    public void setMeasurements(ArrayList<Measurement> measurements)
    {
        this.measurements = measurements;
    }

    public static final int FEMALE = 1;
    public static final int MALE = 2;

    public Child()
    {

    }

    public Child(int _id, String firstName, String lastName, int birthdayDayOfMonth, int birthdateMonth, int birthdateYear, int sex)
    {
        this._id = _id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdateDayOfMonth = birthdayDayOfMonth;
        this.birthdateMonth = birthdateMonth;
        this.birthdateYear = birthdateYear;
        this.sex = sex;
    }

    public void set_id(int _id)
    {
        this._id = _id;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setSex(int sex)
    {
        this.sex = sex;
    }

    public int get_id()
    {
        return _id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public int getSex()
    {
        return sex;
    }

    public Bitmap getPortrait()
    {
        return portrait;
    }

    public void setPortrait(Bitmap portrait)
    {
        this.portrait = portrait;
    }

    public int getBirthdateDayOfMonth()
    {
        return birthdateDayOfMonth;
    }

    public void setBirthdateDayOfMonth(int birthdateDayOfMonth)
    {
        this.birthdateDayOfMonth = birthdateDayOfMonth;
    }

    public int getBirthdateMonth()
    {
        return birthdateMonth;
    }

    public void setBirthdateMonth(int birthdateMonth)
    {
        this.birthdateMonth = birthdateMonth;
    }

    public int getBirthdateYear()
    {
        return birthdateYear;
    }

    public void setBirthdateYear(int birthdateYear)
    {
        this.birthdateYear = birthdateYear;
    }
}
