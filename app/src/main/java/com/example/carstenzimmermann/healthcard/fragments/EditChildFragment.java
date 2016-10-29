package com.example.carstenzimmermann.healthcard.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.example.carstenzimmermann.healthcard.DataManager;
import com.example.carstenzimmermann.healthcard.R;
import com.example.carstenzimmermann.healthcard.entities.Child;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Carsten Zimmermann on 25.10.2016.
 */

public class EditChildFragment extends Fragment
{
    static final String CHILD = "child";
    EditChildFragmentListener listener;
    public static String EDIT_CHILD_FRAGMENT_LISTENER = "listener";
    public static final String TASK_NEW_CHILD = "new_child";
    public static final String TASK_EDIT_CHILD = "edit_child";
    public static final String KEY_TASK = "task";
    private int childId;
    private String task;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.child_edit, container, false);
        listener = (EditChildFragmentListener)this.getActivity();
        Button btSave = (Button) view.findViewById(R.id.btSave);
        Button btCancel = (Button) view.findViewById(R.id.btCancel);
        final EditText etFirstName = (EditText)view.findViewById(R.id.etFirstName);
        final EditText etLastName = (EditText)view.findViewById(R.id.etLastName);
        final EditText etBirthday = (EditText)view.findViewById(R.id.etBirthday);
        final RadioButton rbFemale = (RadioButton) view.findViewById(R.id.rbFemale);
        RadioButton rbMale = (RadioButton) view.findViewById(R.id.rbMale);
        ImageButton ibPortrait = (ImageButton) view.findViewById(R.id.ibPortrait);

        Bundle bundle = (Bundle)this.getArguments();
        task = bundle.getString(KEY_TASK);
        if (TASK_EDIT_CHILD.equals(task))
        {
            //TODO: Add code to modify the portrait
            if (bundle != null)
            {
                childId = bundle.getInt(Child.KEY_ID);
                etFirstName.setText(bundle.getString(Child.KEY_FIRST_NAME));
                etLastName.setText(bundle.getString(Child.KEY_LAST_NAME));
                final SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
                etBirthday.setText(df.format(bundle.getLong(Child.KEY_BIRTHDATE)));
                if (Child.FEMALE == bundle.getInt(Child.KEY_SEX))
                {
                    rbFemale.setChecked(true);
                    rbMale.setChecked(false);
                }
                else
                {
                    rbFemale.setChecked(false);
                    rbMale.setChecked(true);
                }
            }
        }
        else
        {
            childId = 0; // indicates, that this child is not saved yet
        }

        btSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Child child = new Child();
                child.set_id(childId);
                child.setFirstName(etFirstName.getText().toString());
                child.setLastName(etLastName.getText().toString());
                SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
                try
                {
                    child.setBirthdate(df.parse(etBirthday.getText().toString()));
                }
                catch (ParseException e)
                {
                    e.printStackTrace();
                    //TODO: Handle exeption properly (Show message for exaple and cancle navigation
                    Log.d(this.getClass().getName(), "The birthdate could not be parsed. Check for correct format.");
                }
                if (rbFemale.isChecked())
                {
                    child.setSex(Child.FEMALE);
                }
                else
                {
                    child.setSex(Child.MALE);
                }

                listener.onSaveChildClicked(child);
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

        return view;
    }

    public interface EditChildFragmentListener
    {
        public void onSaveChildClicked(Child child);
        public void onCancelClicked();
    }
}
