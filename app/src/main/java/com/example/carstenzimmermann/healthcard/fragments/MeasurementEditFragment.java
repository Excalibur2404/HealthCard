package com.example.carstenzimmermann.healthcard.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.carstenzimmermann.healthcard.DataManager;
import com.example.carstenzimmermann.healthcard.R;
import com.example.carstenzimmermann.healthcard.entities.Measurement;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Carsten Zimmermann on 15.11.2016.
 */

public class MeasurementEditFragment extends Fragment
{
    public static final int DATE_REQUESTER_ID = 2;


    //measurement properties
    private int _id;
    public static final String ID = "id";
    private int childId;
    public static final String CHILD_ID = "childId";
    private float weight;
    public static final String WEIGHT = "weight";

    private DateFormat df;
    private DataManager dataManager;
    MeasurementEditFragmentListener listener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());
        this.dataManager = DataManager.getInstance();
        if (savedInstanceState != null)
        {
            _id = savedInstanceState.getInt(ID);
            childId = savedInstanceState.getInt(CHILD_ID);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putInt(ID, _id);
        outState.putInt(CHILD_ID, childId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.measurement_edit, container, false);
        listener = (MeasurementEditFragmentListener) this.getActivity();
        Button btSave = (Button) view.findViewById(R.id.btSave);
        Button btCancel = (Button) view.findViewById(R.id.btCancel);
        final EditText tvDate = (EditText) view.findViewById(R.id.tvDate);
        tvDate.setText(df.format(Calendar.getInstance().getTime()));
        EditText etWeight = (EditText) view.findViewById(R.id.etWeight);
        etWeight.setText(Float.toString(weight));
        EditText etHeight = (EditText) view.findViewById(R.id.etHeight);
//        EditText etBMI = (EditText) view.findViewById(R.id.etBmi);

        btSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                validateAndSave();
            }
        });

        tvDate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Calendar cal = Calendar.getInstance();
                Date date;
                try
                {
                    date = df.parse(tvDate.getText().toString());
                    cal.setTime(date);


                }
                catch (ParseException e)
                {
                    Log.w(this.getClass().getName(), "Could not parse the date value "
                            + tvDate.getText() + " to a date using the format "
                            + df.getNumberFormat() + ". Using the current date instead.");
                }
                listener.onDateEditClicked(
                        DATE_REQUESTER_ID,
                        cal.get(Calendar.DAY_OF_MONTH),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.YEAR));
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                listener.onCancelClicked();
            }
        });

        etWeight.setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                updateBMI();
                return false;
            }
        });

        etHeight.setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                updateBMI();
                return false;
            }
        });


        return view;
    }

    private void updateBMI()
    {
        EditText etHeight = (EditText) getView().findViewById(R.id.etHeight);
        EditText etWeight = (EditText) getView().findViewById(R.id.etWeight);
        EditText etBMI = (EditText) getView().findViewById(R.id.etBmi);
        float weight = 0f;
        float height = 0f;
        try
        {
            if (etWeight.getText() != null) weight = Float.parseFloat(etWeight.getText().toString());
            if (etHeight.getText() != null) height = Float.parseFloat(etHeight.getText().toString());
        }
        catch (NumberFormatException e)
        {
            // stop updating and return, try again after next edit
            Log.d(this.getClass().getName(), "Parsing weight or height failed. Stopping update of BMI.");
            return;
        }

        if (weight != 0f && height != 0f)
        {
            Log.d(this.getClass().getName(), "Updating BMI. Weight is " + weight + " and height is " +
                    height + ". Weight / height * height is " + (weight / height * height));
            etBMI.setText(Float.toString(Math.round(weight / (height * height) * 100) / 100));
        }
        else
        {
            Log.d(this.getClass().getName(), "One number is 0. Setting empty String as BMI.");
            etBMI.setText("");
        }
    }

    private void saveMeasurements()
    {
        Measurement measurement = new Measurement();

        TextView tvDate = (TextView) getView().findViewById(R.id.tvDate);
        EditText etHeight = (EditText) getView().findViewById(R.id.etHeight);
        EditText etWeight = (EditText) getView().findViewById(R.id.etWeight);

        measurement.set_id(_id);
        measurement.setChildId(childId);
        Date measurementDate = null;
        try
        {
            measurementDate = df.parse(tvDate.getText().toString());
        }
        catch (ParseException e)
        {
            showValidationMessage(R.string.ValidationMessageDateWrong);
            return;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(measurementDate);
        measurement.setDayOfMonth(cal.get(Calendar.DAY_OF_MONTH));
        measurement.setMonth(cal.get(Calendar.MONTH));
        measurement.setYear(cal.get(Calendar.YEAR));

        if ("".equals(etHeight.getText().toString()) == false)
        {
            measurement.setHeight(Float.valueOf(etHeight.getText().toString()));
        }
        if ("".equals(etWeight.getText().toString()) == false)
        {
            measurement.setWeight(Float.valueOf(etWeight.getText().toString()));
        }

        dataManager.saveMeasurement(measurement);
        listener.onMeasurementSavedClicked();
        Log.d(this.getClass().getName(),
                        "Saving measurement with values _id = '" + measurement.get_id() + "', " +
                        "child_id = '" + measurement.getChildId() + "', " +
                        "dayOfMonth = '" + measurement.getDayOfMonth() + "', " +
                        "month = '" + measurement.getMonth() + "', " +
                        "year = '" + measurement.getYear() + "', " +
                        "weight = '" + measurement.getWeight() + "', " +
                        "height = '" + measurement.getHeight() + "'.");
    }

    public void clearMeasurement()
    {
        TextView tvDate = (TextView) getView().findViewById(R.id.tvDate);
        EditText etHeight = (EditText) getView().findViewById(R.id.etHeight);
        EditText etWeight = (EditText) getView().findViewById(R.id.etWeight);

        tvDate.setText(df.format(Calendar.getInstance().getTime()));
        etHeight.setText("");
        etWeight.setText("");
        _id = 0;
    }

    public void displayMeasurement(int measurementSetId)
    {
        TextView tvDate = (TextView) getView().findViewById(R.id.tvDate);
        EditText etHeight = (EditText) getView().findViewById(R.id.etHeight);
        EditText etWeight = (EditText) getView().findViewById(R.id.etWeight);
        EditText etBmi = (EditText) getView().findViewById(R.id.etBmi);

        _id = measurementSetId;
        Measurement measurement = dataManager.getMeasurement(_id);
        if (measurement != null)
        {
            childId = measurement.getChildId();
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.DAY_OF_MONTH, measurement.getDayOfMonth());
            cal.set(Calendar.MONTH, measurement.getMonth());
            cal.set(Calendar.YEAR, measurement.getYear());
            tvDate.setText(df.format(cal.getTime()));
            if (measurement.getHeight() != null)
            {
                etHeight.setText(Float.toString(measurement.getHeight()));
            }
            else
            {
                etHeight.setText("");
            }
            if (measurement.getWeight() != null)
            {
                etWeight.setText(Float.toString(measurement.getWeight()));
            }
            else
            {
                etWeight.setText("");
            }
            updateBMI();
        }
    }

    public void setMeasurementDate(int dayOfMonth, int month, int year)
    {
        TextView tvDate = (TextView) getView().findViewById(R.id.tvDate);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.YEAR, year);
        tvDate.setText(df.format(cal.getTime()));
    }

    public void validateAndSave()
    {
        TextView tvDate = (TextView) getView().findViewById(R.id.tvDate);
        EditText etHeight = (EditText) getView().findViewById(R.id.etHeight);
        EditText etWeight = (EditText) getView().findViewById(R.id.etWeight);

        if ("".equals(etHeight.getText().toString()) && "".equals(etWeight.getText().toString()))
        {
            showValidationMessage(R.string.ValidationMessageNoMeasurementValues);
            return;
        }
        if ("".equals(tvDate.getText().toString()))
        {
            showValidationMessage(R.string.ValidationMessageNoMeasurementDate);
            return;
        }
        saveMeasurements();
    }

    private void showValidationMessage(int messageStringId)
    {
        ValidationDialogFragment dialog = new ValidationDialogFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ValidationDialogFragment.KEY_BUTTON_TEXT_RESOURCE_ID, R.string.ok);
        arguments.putInt(ValidationDialogFragment.KEY_MESSAGE_RESOURCE_ID, messageStringId);
        arguments.putInt(ValidationDialogFragment.KEY_TITLE_RESOURCE_ID, R.string.ValidationTitle);
        dialog.setArguments(arguments);
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(dialog, "CCONSISTENCY_CHECK");
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void setChildId(int childId)
    {
        this.childId = childId;
    }

    public interface MeasurementEditFragmentListener
    {
        public void onDateEditClicked(int dateRequesterId, int dayOfMonth, int month, int year);
        public void onMeasurementSavedClicked();
        public void onCancelClicked();
    }
}
