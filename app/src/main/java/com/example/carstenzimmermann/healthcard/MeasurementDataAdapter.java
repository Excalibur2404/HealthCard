package com.example.carstenzimmermann.healthcard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.carstenzimmermann.healthcard.entities.Measurement;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Carsten Zimmermann on 02.01.2017.
 */

public class MeasurementDataAdapter extends BaseAdapter implements ListAdapter, Filterable
{
    ArrayList<Measurement> unfilteredMeasurementList;
    ArrayList<Measurement> filteredMeasurementList;
    Context context;
    IMeasurementDataAdapterListener listener;
    DateFormat df;
    private MeasurementDataFilter filter = new MeasurementDataFilter();

    public MeasurementDataAdapter(Context c, IMeasurementDataAdapterListener listener)
    {
        DataManager dataManager = DataManager.getInstance();
        unfilteredMeasurementList = dataManager.getMeasurements();
        this.context = c;
        this.listener = listener;
        this.df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());
    }


    @Override
    public int getCount()
    {
        if (filteredMeasurementList != null)
        {
            return filteredMeasurementList.size();
        }
        else
        {
            return 0;
        }
    }

    @Override
    public Object getItem(int position)
    {
        if (filteredMeasurementList != null)
        {
            return filteredMeasurementList.get(position);
        }
        else
        {
            return null;
        }
    }

    @Override
    public long getItemId(int position)
    {
        if (filteredMeasurementList != null)
        {
            return ((Measurement) filteredMeasurementList.get(position)).get_id();
        }
        else
        {
            return 0;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        //Inflate the layout. First get the layout inflater system service
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //inflate the layout
        View row = inflater.inflate(R.layout.measurement_entry, parent, false);

        // now fill the row with data
        TextView measurementDate = (TextView) row.findViewById(R.id.tv_measurement_date);
        TextView height = (TextView) row.findViewById(R.id.tv_height);
        TextView weight = (TextView) row.findViewById(R.id.tv_weight);
        TextView bmi = (TextView) row.findViewById(R.id.tv_bmi);

        Measurement measurement = filteredMeasurementList.get(position);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, measurement.getDayOfMonth());
        cal.set(Calendar.MONTH, measurement.getMonth());
        cal.set(Calendar.YEAR, measurement.getYear());
        measurementDate.setText(df.format(cal.getTime()));
        height.setText(Float.toString(measurement.getHight()));
        weight.setText(Float.toString(measurement.getWeight()));
        if (measurement.getHight() != 0 && measurement.getWeight() != 0)
        {
            bmi.setText(Float.toString(measurement.getWeight()/(measurement.getHight() * measurement.getHight())));
        }
        else
        {
            bmi.setText("");
        }
        return row;
    }

    public void setChildFilter(int childId)
    {
        filter.filter(Integer.toString(childId));
    }

    @Override
    public Filter getFilter()
    {
        return filter;
    }

    public interface IMeasurementDataAdapterListener
    {
        public void onEditClicked(int id);
        public void onDeleteClicked(int id);
    }

    private class MeasurementDataFilter extends Filter
    {
        @Override
        protected FilterResults performFiltering(CharSequence constraint)
        {
            FilterResults results = new FilterResults();
            ArrayList<Measurement> resultList = new ArrayList<Measurement>();
            for (Measurement measurement:unfilteredMeasurementList)
            {
                if ("".compareTo(constraint.toString()) != 0)
                {
                    if (Integer.toString(measurement.getChildId()).compareTo(constraint.toString()) == 0)
                    {
                        resultList.add(measurement);
                    }
                }
                else
                {
                    resultList.add(measurement);
                }
            }
            results.values = resultList;
            results.count = resultList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results)
        {
            filteredMeasurementList = (ArrayList<Measurement>) results.values;
            notifyDataSetChanged();
        }
    }
}
