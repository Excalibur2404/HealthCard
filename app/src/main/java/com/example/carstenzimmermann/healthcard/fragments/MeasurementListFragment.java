package com.example.carstenzimmermann.healthcard.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.carstenzimmermann.healthcard.DataManager;
import com.example.carstenzimmermann.healthcard.MeasurementDataAdapter;
import com.example.carstenzimmermann.healthcard.R;

/**
 * Created by Carsten Zimmermann on 02.01.2017.
 */

public class MeasurementListFragment extends Fragment implements MeasurementDataAdapter.IMeasurementDataAdapterListener
{
    DataManager dataManager;
    IMeasurementListFragmentListener listener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Activity activity = getActivity();
        if (activity instanceof IMeasurementListFragmentListener)
            listener = (IMeasurementListFragmentListener) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.measurement_list, container, false);
        ListView lv_measurement_list = (ListView) view.findViewById(R.id.lv_measurement_list);
        lv_measurement_list.setAdapter(new MeasurementDataAdapter(0, getActivity().getApplicationContext(), this));
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

    public void setChildFilter(int childId)
    {
        ListView lv_measurement_list = (ListView) getView().findViewById(R.id.lv_measurement_list);
        MeasurementDataAdapter adapter = (MeasurementDataAdapter)lv_measurement_list.getAdapter();
        adapter.setChildFilter(childId);
    }

    public interface IMeasurementListFragmentListener
    {
        public void onDeleteMeasurementClicked(int id);
        public void onEditMeasurementClicked (int id);
    }
}
