package com.example.carstenzimmermann.healthcard.entities;

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
    private Date birthdate;
    private int sex;
    private ArrayList<Measurement> measurements;

    public static String KEY_ID = "child_id";
    public static String KEY_FIRST_NAME = "child_first_name";
    public static String KEY_LAST_NAME = "child_last_name";
    public static String KEY_BIRTHDATE = "child_birthdate";
    public static String KEY_SEX = "child_key_sex";

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

    public Child(int _id, String firstName, String lastName, Date birthdate, int sex)
    {
        this._id = _id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.sex = sex;
    }

    public void set_id(int _id)
    {
        this._id = _id;
    }

    public void setBirthdate(Date birthdate)
    {
        this.birthdate = birthdate;
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

    public Date getBirthdate()
    {
        return birthdate;
    }

    public long getBirthdateLong()
    {
        if (birthdate != null)
        {
            return birthdate.getTime();
        }
        else
        {
            return 0;
        }

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
}
