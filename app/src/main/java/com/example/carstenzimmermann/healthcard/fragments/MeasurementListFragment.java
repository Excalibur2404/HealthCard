package com.example.carstenzimmermann.healthcard.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.carstenzimmermann.healthcard.DataManager;
import com.example.carstenzimmermann.healthcard.MeasurementDataAdapter;
import com.example.carstenzimmermann.healthcard.R;

/**
 * Created by Carsten Zimmermann on 02.01.2017.
 */
    //todo: Add header line to list
    //todo: Make date be displayed according to os settings (year is still displayed with two characters instead of 4 as defined in the settings)
public class MeasurementListFragment extends Fragment implements MeasurementDataAdapter.IMeasurementDataAdapterListener
{
    private DataManager dataManager;
    private IMeasurementListFragmentListener listener;

    public static final String KEY_CHILD_ID = "child_id";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Activity activity = getActivity();
        if (activity instanceof IMeasurementListFragmentListener)
            listener = (IMeasurementListFragmentListener) activity;
        dataManager = DataManager.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.measurement_list, container, false);
        ListView lv_measurement_list = (ListView) view.findViewById(R.id.lv_measurement_list);
        int childId = getArguments().getInt(KEY_CHILD_ID);
        MeasurementDataAdapter adapter = new MeasurementDataAdapter(getActivity().getApplicationContext(), this);
        adapter.setChildFilter(childId);
        lv_measurement_list.setAdapter(adapter);
        return view;
    }

    @Override
    public void onEditClicked(int id)
    {
        //todo: implement method
    }

    @Override
    public void onDeleteClicked(int id)
    {
        //todo: implement method
    }

    public interface IMeasurementListFragmentListener
    {
        public void onDeleteMeasurementClicked(int id);
        public void onEditMeasurementClicked (int id);
    }
}
