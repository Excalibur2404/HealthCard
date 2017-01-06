package com.example.carstenzimmermann.healthcard;

/**
 * Created by Carsten Zimmermann on 22.10.2016.
 */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.carstenzimmermann.healthcard.entities.Child;
import com.example.carstenzimmermann.healthcard.entities.Measurement;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Carsten Zimmermann on 09.10.2016.
 */

public class DataManager
{
    public static DataManager dataManager;
    private ArrayList<Child> children;
    private ArrayList<Measurement> measurements;
    private int nextFreeChildId;
    private int nextFreeMeasurementId;

    public DataManager()
    {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        this.children = new ArrayList<Child>();
        children.add(new Child(
                1,
                "Jonna",
                "Zimmermann",
                29,
                4,
                2015,
                Child.FEMALE));

        children.add(new Child(
                2,
                "Felix",
                "Ellekotten",
                19,
                11,
                2015,
                Child.MALE));

        children.add(new Child(
                3,
                "Max",
                "Mustermann",
                1,
                1,
                2014,
                Child.MALE));
        nextFreeChildId = 4;

        this.measurements = new ArrayList<Measurement>();
        // add some values for Jonna
//                                       ID   ChildID  Day  Month  Year  Weight  Height
        measurements.add(new Measurement(1   ,1       ,2   ,5     ,2015 ,3.2f   ,0.53f));
        measurements.add(new Measurement(2   ,1       ,9   ,6     ,2015 ,3.7f   ,0.57f));
        measurements.add(new Measurement(3   ,1       ,2   ,7     ,2015 ,4.6f   ,0.59f));
        measurements.add(new Measurement(4   ,1       ,9   ,9     ,2015 ,7.1f   ,0.65f));
        measurements.add(new Measurement(5   ,1       ,10  ,11    ,2015 ,8.4f   ,0.69f));
        measurements.add(new Measurement(6   ,1       ,9   ,12    ,2015 ,8.6f   ,0.70f));
        measurements.add(new Measurement(7   ,1       ,12  ,1     ,2016 ,8.7f   ,0.74f));
        measurements.add(new Measurement(8   ,1       ,18  ,2     ,2016 ,8.8f   ,0.0f));
        measurements.add(new Measurement(9   ,1       ,2   ,3     ,2016 ,9.0f   ,0.0f));
        measurements.add(new Measurement(10  ,1       ,13  ,4     ,2016 ,9.9f   ,0.77f));
        measurements.add(new Measurement(11  ,1       ,19  ,4     ,2016 ,9.9f   ,0.775f));
        measurements.add(new Measurement(12  ,1       ,21  ,7     ,2016 ,10.8f  ,0.82f));
        measurements.add(new Measurement(13  ,1       ,9   ,8     ,2016 ,10.7f  ,0.0f));
        measurements.add(new Measurement(14  ,1       ,23  ,9     ,2016 ,11f    ,0.85f));
        measurements.add(new Measurement(15  ,1       ,9   ,11    ,2016 ,11.5f  ,0.86f));
        measurements.add(new Measurement(16  ,1       ,3   ,12    ,2016 ,11.6f  ,0.86f));
        measurements.add(new Measurement(17  ,1       ,15  ,12    ,2016 ,11.3f  ,0.86f));

        // add some values for Felix
        measurements.add(new Measurement(18,2,4,1,2016,7.9f,7.9f));
        measurements.add(new Measurement(19,2,5,1,2016,9.0f,9.0f));
        measurements.add(new Measurement(20,2,6,1,2016,10.8f,10.8f));
        nextFreeMeasurementId = 21;
    }

    public Child getChildById(int id)
    {
        for (Child child : children)
        {
            if (child.get_id() == id)
            {
                return child;
            }
        }
        return null;
    }

    public static DataManager getInstance()
    {
        if (dataManager == null)
        {
            dataManager = new DataManager();
        }
        return dataManager;
    }

    public ArrayList<Child> getChildren()
    {
        return children;
    }

    public void deleteChild(int _id)
    {
        for (Iterator<Child> it = children.iterator(); it.hasNext(); )
        {
            Child child = it.next();
            if (_id == child.get_id())
            {
                it.remove();
                return;
            }
        }
    }

    public void saveChild(Child child)
    {
        if (child.get_id() == 0)
        {
            child.set_id(getNewChildId());
            children.add(child);
        }

        for (Child c : children)
        {
            int idToUpdate = child.get_id();
            if (c.get_id() == idToUpdate)
            {
                Log.d(this.getClass().getName(), "Updating child...");
                children.set(children.indexOf(c), child);
                Log.d(this.getClass().getName(), "...child updated.");
            }
        }
    }

    private int getNewChildId()
    {
        int id = nextFreeChildId;
        nextFreeChildId++;
        return id;
    }

    private int getNewMeasurementId()
    {
        int id = nextFreeMeasurementId;
        nextFreeMeasurementId++;
        return id;
    }

    private class CustomArrayListAdapter extends BaseAdapter
    {
        private ArrayList<Child> children = new ArrayList();
        private LayoutInflater inflater;

        public CustomArrayListAdapter(Context context)
        {
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount()
        {
            return children.size();
        }

        @Override
        public Object getItem(int position)
        {
            return children.indexOf(position);
        }

        @Override
        public long getItemId(int position)
        {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            if (convertView == null)
            {
                convertView = inflater.inflate(R.layout.child_entry, null);
                TextView firstName = (TextView)convertView.findViewById(R.id.tv_child_first_name);
            }
            return null;
        }
    }

    public void saveMeasurement(Measurement measurement)
    {
        if (measurement.get_id() == 0)
        {
            measurement.set_id(getNewMeasurementId());
            measurements.add(measurement);
        }
        else
        {
            for (Measurement m : measurements)
            {
                if (m.get_id() == measurement.get_id())
                {
                    Log.d(this.getClass().getName(), "Updating measurement...");
                    measurements.set(measurements.indexOf(m), measurement);
                    Log.d(this.getClass().getName(), "...measurement updated.");
                }
            }
        }
    }

    public ArrayList<Measurement> getMeasurements(int childID)
    {
        //todo: Implement sorting
        ArrayList<Measurement> resultSet = new ArrayList<Measurement>();
        for (Measurement measurement: measurements)
        {
            if (measurement.getChildId() == childID)
            {
                resultSet.add(measurement);
            }
        }
        return resultSet;
    }

    public Measurement getMeasurement(int measurementId)
    {
        for (Measurement measurement:measurements)
        {
            if (measurement.get_id() == measurementId)
            {
                return measurement;
            }
        }
        return null;
    }

    public ArrayList<Measurement> getMeasurements()
    {
        return measurements;
    }

    public void deleteMeasurement (int id)
    {
        for (Measurement measurement:measurements)
        {
            if (measurement.get_id() == id)
            {
                measurements.remove(measurements.indexOf(measurement));
                return;
            }
        }
    }
}