package com.example.carstenzimmermann.healthcard.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.carstenzimmermann.healthcard.DataManager;
import com.example.carstenzimmermann.healthcard.R;
import com.example.carstenzimmermann.healthcard.TestDataAdapter;

/**
 * Created by Carsten Zimmermann on 25.10.2016.
 */

public class ChildListFragment extends Fragment implements TestDataAdapter.TestDataAdapterListener
{
    DataManager dataManager;
    ChildListFragmentListener listener;

    static final String CHILD_LIST_FRAGMENT_LISTENER = "listener";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Activity activity = getActivity();
        if (activity instanceof ChildListFragmentListener)
            listener = (ChildListFragmentListener) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
         View view = inflater.inflate(R.layout.child_list, container, false);
        //TODO: Was bedeutet das 'attach to root'?

        dataManager = DataManager.getInstance();
        ListView lvChildren = (ListView)view.findViewById(R.id.child_list);
        lvChildren.setAdapter(new TestDataAdapter(getActivity().getApplicationContext(), this));

        FloatingActionButton floatingActionButton = (FloatingActionButton)view.findViewById(R.id.btAddChild);
        floatingActionButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                listener.onAddChildClicked();
            }
        });

        return view;
    }

    @Override
    public void onEditClicked(int id)
    {
        listener.editChild(id);
    }

    @Override
    public void onAddMeasurementClicked(int childId)
    {
        listener.onAddMeasurementClicked(childId);
    }

    @Override
    public void onDeleteClicked(int id)
    {
        listener.onDeleteClicked(id);
    }

    public void notifyDataSetChanged()
    {
        ListView lvChildren = (ListView)getView().findViewById(R.id.child_list);
        TestDataAdapter testDataAdapter = (TestDataAdapter)lvChildren.getAdapter();
        testDataAdapter.notifyDataSetChanged();
    }

    public interface ChildListFragmentListener
    {
        public void editChild(int id);
        public void onAddChildClicked();
        public void onAddMeasurementClicked(int childId);
        public void onDeleteClicked(int id);
    }
}
