package com.example.carstenzimmermann.healthcard;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.icu.util.Measure;
import android.os.PersistableBundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.widget.DatePicker;

import com.example.carstenzimmermann.healthcard.entities.Child;
import com.example.carstenzimmermann.healthcard.entities.Measurement;
import com.example.carstenzimmermann.healthcard.fragments.ChartFragment;
import com.example.carstenzimmermann.healthcard.fragments.ChildListFragment;
import com.example.carstenzimmermann.healthcard.fragments.EditChildFragment;
import com.example.carstenzimmermann.healthcard.fragments.MeasurementEditFragment;
import com.example.carstenzimmermann.healthcard.fragments.MeasurementListFragment;

import net.davidcesarino.android.common.ui.DatePickerDialogFragment;

import java.util.List;

public class MainActivity
        extends     AppCompatActivity
        implements  ChildListFragment.ChildListFragmentListener,
                    EditChildFragment.EditChildFragmentListener,
                    DatePickerDialog.OnDateSetListener,
                    DatePickerDialog.OnCancelListener,
                    MeasurementEditFragment.MeasurementEditFragmentListener,
                    MeasurementListFragment.IMeasurementListFragmentListener
{
    DataManager dataManager;
    public final String CHILD_LIST_FRAGMENT_TAG = "child_list_fragment";
    public final String CHART_FRAGMENT_TAG = "chart_fragment";
    public final String EDIT_CHILD_FRAGMENT_TAG = "edit_child_fragment";
    public final String MEASUREMENT_EDIT_FRAGMENT_TAG = "measurment_edit_fragment";
    public final String MEASUREMENT_LIST_FRAGMENT_TAG = "measurement_list_fragment";
    private int dateRequesterId;
    private final String DATE_REQUESTER_ID = "date_requester_id";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) dateRequesterId = savedInstanceState.getInt(DATE_REQUESTER_ID);
        if (dataManager == null) dataManager = DataManager.getInstance();
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        ChildListFragment childListFragment = (ChildListFragment)fm.findFragmentByTag(CHILD_LIST_FRAGMENT_TAG);
        if (childListFragment == null)
        {
            childListFragment = new ChildListFragment();
            FragmentTransaction fta = fm.beginTransaction();
            fta.replace(R.id.fragmentContainer, childListFragment, CHILD_LIST_FRAGMENT_TAG);
            fta.commit();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putInt(DATE_REQUESTER_ID, dateRequesterId);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState)
    {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
    }

    @Override
    public void editChild(int id)
    {
        FragmentManager fm = getSupportFragmentManager();
        EditChildFragment editChildFragment = (EditChildFragment) fm.findFragmentByTag(EDIT_CHILD_FRAGMENT_TAG);
        if (editChildFragment == null)
        {
            editChildFragment = new EditChildFragment();
        }
        Child child = dataManager.getChildById(id);

        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, editChildFragment, EDIT_CHILD_FRAGMENT_TAG);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
        fm.executePendingTransactions();
        editChildFragment.displayChild(child);
    }

    @Override
    public void onSaveChildClicked(Child child)
    {
        dataManager.saveChild(child);
        FragmentManager fm = getSupportFragmentManager();
        ChildListFragment childListFragment = (ChildListFragment)fm.findFragmentByTag(CHILD_LIST_FRAGMENT_TAG);
        if (childListFragment == null)
        {
            childListFragment = new ChildListFragment();
        }
        FragmentTransaction fta = fm.beginTransaction();
        fta.replace(R.id.fragmentContainer, childListFragment);
        fta.addToBackStack(null);
        fta.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        fta.commit();
    }

    @Override
    public void onCancelClicked()
    {
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStack();
    }

    @Override
    public void onAddChildClicked()
    {
        FragmentManager fm = getSupportFragmentManager();
        EditChildFragment editChildFragment= (EditChildFragment) fm.findFragmentByTag(EDIT_CHILD_FRAGMENT_TAG);
        if (editChildFragment == null)
        {
            editChildFragment = new EditChildFragment();
        }
        FragmentTransaction fta = fm.beginTransaction();
        fta.replace(R.id.fragmentContainer, editChildFragment);
        fta.addToBackStack(null);
        fta.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fta.commit();
        fm.executePendingTransactions();
        editChildFragment.clearChild();
    }

    @Override
    public void onAddMeasurementClicked(int childId)
    {
        FragmentManager fm = getSupportFragmentManager();
        MeasurementEditFragment measurementEditFragment = (MeasurementEditFragment) fm.findFragmentByTag(MEASUREMENT_EDIT_FRAGMENT_TAG);
        if (measurementEditFragment == null)
        {
            measurementEditFragment = new MeasurementEditFragment();
        }
        FragmentTransaction fta = fm.beginTransaction();
        fta.replace(R.id.fragmentContainer, measurementEditFragment, MEASUREMENT_EDIT_FRAGMENT_TAG);
        fta.addToBackStack(null);
        fta.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fta.commit();
        fm.executePendingTransactions();
        measurementEditFragment.setChildId(childId);
        measurementEditFragment.clearMeasurement();
    }

    @Override
    public void onEditMeasurementClicked(int id)
    {
        FragmentManager fm = getSupportFragmentManager();
        MeasurementEditFragment measurementEditFragment = (MeasurementEditFragment) fm.findFragmentByTag(MEASUREMENT_EDIT_FRAGMENT_TAG);
        if (measurementEditFragment == null)
        {
            measurementEditFragment = new MeasurementEditFragment();
        }
        FragmentTransaction fta = fm.beginTransaction();
        fta.replace(R.id.fragmentContainer, measurementEditFragment, MEASUREMENT_EDIT_FRAGMENT_TAG);
        fta.addToBackStack(null);
        fta.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fta.commit();
        fm.executePendingTransactions();
        measurementEditFragment.displayMeasurement(id);
    }

    @Override
    public void onMeasurementSavedClicked()
    {
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStack();
        fm.executePendingTransactions();
    }

    @Override
    public void onDeleteClicked(final int id)
    {
        Log.d(this.getClass().getName(), "Showing security question...");

        //This anonymous class handles the confirmation to delete a child entry
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        dataManager.deleteChild(id);
                        FragmentManager fm = getSupportFragmentManager();
                        ChildListFragment childListFragment = (ChildListFragment)fm.findFragmentByTag(CHILD_LIST_FRAGMENT_TAG);
                        childListFragment.notifyDataSetChanged();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //do nothing
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.myDialog));
        builder
                .setMessage(getString(R.string.security_question))
                .setPositiveButton(R.string.yes, dialogClickListener)
                .setNegativeButton(R.string.no, dialogClickListener)
                .show();
    }

    @Override
    public void onDisplayChartClicked(int childId)
    {
        Child child = dataManager.getChildById(childId);
        List<Measurement> measurements = dataManager.getMeasurements(childId);

        if (measurements.size() == 0)
        {
            //This anonymous class handles the confirmation to delete a child entry
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_NEUTRAL:
                            //do nothing
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.myDialog));
            builder
                    .setMessage(getString(R.string.no_measurement_data))
                    .setNeutralButton(R.string.ok, dialogClickListener)
                    .show();
        }
        else
        {
            FragmentManager fm = getSupportFragmentManager();
            ChartFragment chartFragment = null;
            if (fm.findFragmentByTag(CHART_FRAGMENT_TAG) == null)
            {
                chartFragment = new ChartFragment();
                Bundle bundle = new Bundle();
                bundle.putString(ChartFragment.KEY_GRAPH_LABEL, child.getFirstName() + " " + child.getLastName());
                bundle.putInt(ChartFragment.KEY_CHILD_ID, child.get_id());
                bundle.putInt(ChartFragment.KEY_BIRTHDATE_YEAR, child.getBirthdateYear());
                bundle.putInt(ChartFragment.KEY_BIRTHDATE_MONTH, child.getBirthdateMonth());
                bundle.putInt(ChartFragment.KEY_BIRTHDATE_DAY_OF_MONTH, child.getBirthdateDayOfMonth());
                bundle.putInt(ChartFragment.KEY_SEX, child.getSex());
                chartFragment.setArguments(bundle);
            }
            else
            {
                chartFragment = (ChartFragment) fm.findFragmentByTag(CHART_FRAGMENT_TAG);
            }

            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainer, chartFragment, CHART_FRAGMENT_TAG);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onDisplayMeasurementsClicked(int childId)
    {
        Child child = dataManager.getChildById(childId);
        List<Measurement> measurements = dataManager.getMeasurements(childId);

        if (measurements.size() == 0)
        {
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_NEUTRAL:
                            //do nothing
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.myDialog));
            builder
                    .setMessage(getString(R.string.no_measurement_data))
                    .setNeutralButton(R.string.ok, dialogClickListener)
                    .show();
        }
        else
        {
            FragmentManager fm = getSupportFragmentManager();
            MeasurementListFragment measurementListFragment = (MeasurementListFragment) fm.findFragmentByTag(MEASUREMENT_LIST_FRAGMENT_TAG);
            if (measurementListFragment == null)
            {
                measurementListFragment = new MeasurementListFragment();
                Bundle bundle = new Bundle();
                bundle.putInt(MeasurementListFragment.KEY_CHILD_ID, childId);
                measurementListFragment.setArguments(bundle);
            }
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainer, measurementListFragment, MEASUREMENT_LIST_FRAGMENT_TAG);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onDateEditClicked(int dateRequesterId, int dayOfMonth, int month, int year)
    {
        //trying the workaround for the Jellybean Bug in the DatePickerDialog:
        Bundle b = new Bundle();
        b.putInt(DatePickerDialogFragment.YEAR, year);
        b.putInt(DatePickerDialogFragment.MONTH, month);
        b.putInt(DatePickerDialogFragment.DATE, dayOfMonth);
        DialogFragment picker = new DatePickerDialogFragment();
        picker.setArguments(b);
        //todo: Store away the requester id in the appropriate method. Otherwise the ID gets lost when the view is recreated during screen orientation change.
        this.dateRequesterId = dateRequesterId;
        picker.show(this.getSupportFragmentManager(), "DATE_PICKER_FRAGMENT");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
    {
        Log.d(this.getClass().getName(), "Executing onDateSet()...");
        FragmentManager fm = getSupportFragmentManager();
        if (this.dateRequesterId == EditChildFragment.DATE_REQUESTER_ID)
        {
            EditChildFragment editChildFragment = (EditChildFragment)fm.findFragmentByTag(EDIT_CHILD_FRAGMENT_TAG);
            if (editChildFragment != null)
            {
                editChildFragment.setBirthdate(year, month, dayOfMonth);
            }
        }
        else if (this.dateRequesterId == MeasurementEditFragment.DATE_REQUESTER_ID)
            {
            MeasurementEditFragment measurementEditFragment = (MeasurementEditFragment) fm.findFragmentByTag(MEASUREMENT_EDIT_FRAGMENT_TAG);
            if (measurementEditFragment != null)
            {
                measurementEditFragment.setMeasurementDate(dayOfMonth, month, year);
            }
        }
    }

    @Override
    public void onCancel(DialogInterface dialog)
    {
        //do nothing
    }

    @Override
    public void onDeleteMeasurementClicked(int id)
    {
        //todo: implement method
    }


}