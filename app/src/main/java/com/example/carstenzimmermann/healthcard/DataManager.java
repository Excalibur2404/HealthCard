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
    int nextFreeId;

    //TODO: Add method to update a childs data

    public DataManager()
    {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        this.children = new ArrayList<Child>();
        try
        {
            children.add(new Child(
                    1,
                    "Jonna",
                    "Zimmermann",
                    format.parse("29.04.2015"),
                    Child.FEMALE));

            children.add(new Child(
                    2,
                    "Felix",
                    "Ellekotten",
                    format.parse("19.11.2015"),
                    Child.MALE));

            children.add(new Child(
                    3,
                    "Max",
                    "Mustermann",
                    format.parse("01.01.2014"),
                    Child.MALE));
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        nextFreeId = 4;

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
        Iterator<Child> it = children.iterator();
        for (Child child : children)
        {
            if (_id == child.get_id())
            {
                children.remove(child);
            }
        }
    }

    public void saveChild(Child child)
    {
        if (child.get_id() == 0)
        {
            child.set_id(getNewId());
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

    private int getNewId()
    {
        int id = nextFreeId;
        nextFreeId++;
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
//                firstName.setText();
            }
            return null;
        }
    }
}
