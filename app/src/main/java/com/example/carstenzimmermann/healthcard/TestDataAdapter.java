package com.example.carstenzimmermann.healthcard;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.carstenzimmermann.healthcard.entities.Child;

import java.util.ArrayList;

/**
 * Created by Carsten Zimmermann on 22.10.2016.
 */

public class TestDataAdapter extends BaseAdapter implements ListAdapter
{
    ArrayList<Child> children;
    Context context;
    TestDataAdapterListener listener;

    public TestDataAdapter(Context c, TestDataAdapterListener listener)
    {
        DataManager myDataManager = DataManager.getInstance();
        this.children = myDataManager.getChildren();
        this.context = c;
        this.listener = listener;
    }

    @Override
    public int getCount()
    {
        if (children != null)
        {
            return children.size();
        }
        else
        {
            return 0;
        }
    }

    @Override
    public Object getItem(int position)
    {
        if (children != null)
        {
            return children.get(position);
        }
        else
        {
            return null;
        }
    }

    @Override
    public long getItemId(int position)
    {
        if (children != null)
        {
            Child child = children.get(position);
            if (child != null)
            {
                return child.get_id();
            }
            else
            {
                return 0;
            }
        }
        else
        {
            return 0;
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        //Inflate the layout. First get the layout inflater system service
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //inflate the layout
        View row = inflater.inflate(R.layout.child_entry, parent, false);

        // now fill the row with data
        final Child child = (Child) children.get(position);
        ImageView portrait = (ImageView)row.findViewById(R.id.childPicture);
        if (child.getPortrait() != null)
        {
            portrait.setImageBitmap(child.getPortrait());
        }
        TextView tvFirstName = (TextView) row.findViewById(R.id.tv_child_first_name);
        tvFirstName.setText(child.getFirstName());

        ImageButton ibDeleteChild = (ImageButton) row.findViewById(R.id.ib_delete_child);
        ibDeleteChild.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                listener.onDeleteClicked(child.get_id());
            }
        });

        ImageButton ibEditChild = (ImageButton) row.findViewById(R.id.ib_edit);
        ibEditChild.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                listener.onEditClicked(child.get_id());
            }
        });

        ImageButton ibAddMeasurement = (ImageButton) row.findViewById(R.id.ibAddMeasurement);
        ibAddMeasurement.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                listener.onAddMeasurementClicked(child.get_id());
            }
        });

        ImageButton ibShowChart = (ImageButton) row.findViewById(R.id.ibShowChart);
        ibShowChart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                listener.onDisplayChartClicked(child.get_id());
            }
        });

        return row;
    }

    public interface TestDataAdapterListener
    {
        public void onEditClicked(int id);
        public void onAddMeasurementClicked(int childId);
        public void onDeleteClicked(int id);
        public void onDisplayChartClicked(int childId);
    }

}

