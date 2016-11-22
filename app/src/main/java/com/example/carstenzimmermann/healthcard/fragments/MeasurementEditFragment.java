package com.example.carstenzimmermann.healthcard.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    public static int DATE_REQUESTER_ID = 2;

    private int measurementSetId;
    private int childId;
    private DateFormat df;
    private MeasurementEditFragmentListener listener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.measurement_edit, container, false);
        final MeasurementEditFragmentListener listener = (MeasurementEditFragmentListener) this.getActivity();
        Button btSave = (Button) view.findViewById(R.id.btSave);
        Button btCancel = (Button) view.findViewById(R.id.btCancel);
        TextView tvDate = (TextView) view.findViewById(R.id.tvDate);

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
                final MeasurementEditFragmentListener myListener = listener;
                listener.onDateEditClicked(DATE_REQUESTER_ID);
            }
        });

        return view;
    }

    private void saveMeasurements()
    {
        Measurement measurement = new Measurement();

        TextView tvDate = (TextView) getView().findViewById(R.id.tvDate);
        EditText etHeight = (EditText) getView().findViewById(R.id.etHeight);
        EditText etWeight = (EditText) getView().findViewById(R.id.etWeight);

        measurement.set_id(measurementSetId);
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
            measurement.setHight(Float.valueOf(etHeight.getText().toString()));
        }
        if ("".equals(etWeight.getText().toString()) == false)
        {
            measurement.setWeight(Float.valueOf(etWeight.getText().toString()));
        }

        listener.onSaveMeasurementClicked(measurement);
    }

    public void clearMeasurement()
    {
        TextView tvDate = (TextView) getView().findViewById(R.id.tvDate);
        EditText etHeight = (EditText) getView().findViewById(R.id.etHeight);
        EditText etWeight = (EditText) getView().findViewById(R.id.etWeight);

        tvDate.setText("");
        etHeight.setText("");
        etWeight.setText("");
        measurementSetId = 0;
    }

    public void displayMeasurement(int measurementSetId, int childId, int dayOfMonth, int month, int year, int weight, int height)
    {
        TextView tvDate = (TextView) getView().findViewById(R.id.tvDate);
        EditText etHeight = (EditText) getView().findViewById(R.id.etHeight);
        EditText etWeight = (EditText) getView().findViewById(R.id.etWeight);

        this.measurementSetId = measurementSetId;
        this.childId = childId;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.YEAR, year);
        tvDate.setText(df.format(cal.getTime()));
        etHeight.setText(height);
        etWeight.setText(weight);
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
        public void onDateEditClicked(int dateRequesterId);
        public void onSaveMeasurementClicked(Measurement measurement);
        public void onCancelClicked();
    }
}
