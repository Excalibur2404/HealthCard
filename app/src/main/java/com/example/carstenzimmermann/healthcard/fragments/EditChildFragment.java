package com.example.carstenzimmermann.healthcard.fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.CalendarContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.carstenzimmermann.healthcard.DataManager;
import com.example.carstenzimmermann.healthcard.R;
import com.example.carstenzimmermann.healthcard.entities.Child;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Carsten Zimmermann on 25.10.2016.
 */

//TODO: Add Date Picker Dialog to edit the birthdate

public class EditChildFragment extends Fragment
{
    // constants
    public static final String          TASK_NEW_CHILD = "new_child";
    public static final String          TASK_EDIT_CHILD = "edit_child";
    public static final String          KEY_TASK = "task";
    private static int                  PICK_PHOTO_FOR_AVATAR = 1;
    public static final int             DATE_REQUESTER_ID = 1;

    private DateFormat                  df;
    private EditChildFragmentListener   listener;
    private int                         childId;
    private String                      task;
    private boolean                     imageChanged;


    public interface EditChildFragmentListener
    {
        public void onSaveChildClicked(Child child);
        public void onCancelClicked();
        public void onDateEditClicked(int dateRequesterId, int dayOfMonth, int month, int year);
    }

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
        imageChanged = false;
        View view = inflater.inflate(R.layout.child_edit, container, false);
        listener = (EditChildFragmentListener)this.getActivity();
        Button btSave = (Button) view.findViewById(R.id.btSave);
        Button btCancel = (Button) view.findViewById(R.id.btCancel);
        final TextView tvBirthdate = (TextView)view.findViewById(R.id.tvBirthdate);
        final ImageView ibPortrait = (ImageView) view.findViewById(R.id.ibPortrait);

        btSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                validateInput();
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

        ibPortrait.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                pickImage();
            }
        });

        tvBirthdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Calendar cal = Calendar.getInstance();
                Date date;
                try
                {
                    date = df.parse(tvBirthdate.getText().toString());
                    cal.setTime(date);


                }
                catch (ParseException e)
                {
                    Log.w(this.getClass().getName(), "Could not parse the date value "
                            + tvBirthdate.getText() + " to a date using the format "
                            + df.getNumberFormat() + ". Using the current date instead.");
                }
                listener.onDateEditClicked(
                        DATE_REQUESTER_ID,
                        cal.get(Calendar.DAY_OF_MONTH),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.YEAR));
            }
        });
        return view;
    }

    public void displayChild(Child child)
    {
        // get references to the view elements
        Button btSave = (Button) getView().findViewById(R.id.btSave);
        Button btCancel = (Button) getView().findViewById(R.id.btCancel);
        final EditText etFirstName = (EditText)getView().findViewById(R.id.etFirstName);
        final EditText etLastName = (EditText)getView().findViewById(R.id.etLastName);
        final TextView tvBirthdate = (TextView)getView().findViewById(R.id.tvBirthdate);
        final RadioButton rbFemale = (RadioButton) getView().findViewById(R.id.rbFemale);
        final RadioButton rbMale = (RadioButton) getView().findViewById(R.id.rbMale);
        final ImageView ibPortrait = (ImageView) getView().findViewById(R.id.ibPortrait);

        etFirstName.setText(child.getFirstName());
        etLastName.setText(child.getLastName());
        Calendar cal = Calendar.getInstance();
        if (child.getBirthdateDayOfMonth() != 0 || child.getBirthdateMonth() != 0 || child.getBirthdateYear() != 0)
        {
            cal.set(Calendar.YEAR, child.getBirthdateYear());
            cal.set(Calendar.MONTH, child.getBirthdateMonth());
            cal.set(Calendar.DAY_OF_MONTH, child.getBirthdateDayOfMonth());
        }
        tvBirthdate.setText(df.format(cal.getTime()));

        if (Child.FEMALE == child.getSex())
        {
            rbFemale.setChecked(true);
            rbMale.setChecked(false);
        }
        else
        {
            rbFemale.setChecked(false);
            rbMale.setChecked(true);
        }
        if (child.getPortrait() != null)
        {
            ibPortrait.setImageBitmap(child.getPortrait());
        }
        childId = child.get_id();
    }

    public void clearChild()
    {
        // get references to the view elements
        Button btSave = (Button) getView().findViewById(R.id.btSave);
        Button btCancel = (Button) getView().findViewById(R.id.btCancel);
        final EditText etFirstName = (EditText)getView().findViewById(R.id.etFirstName);
        final EditText etLastName = (EditText)getView().findViewById(R.id.etLastName);
        final TextView tvBirthdate = (TextView)getView().findViewById(R.id.tvBirthdate);
        final RadioButton rbFemale = (RadioButton) getView().findViewById(R.id.rbFemale);
        final RadioButton rbMale = (RadioButton) getView().findViewById(R.id.rbMale);
        final ImageView ibPortrait = (ImageView) getView().findViewById(R.id.ibPortrait);

        childId = 0;
        ibPortrait.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_person_placeholder, null));
        etFirstName.setText("");;
        etLastName.setText("");
        Calendar cal = Calendar.getInstance();
        tvBirthdate.setText(df.format(cal.getTime()));
        rbFemale.setChecked(false);
        rbMale.setChecked(false);
    }

    private void pickImage()
    {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_PHOTO_FOR_AVATAR);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        InputStream inputStream = null;
        if (requestCode == PICK_PHOTO_FOR_AVATAR && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                Log.d(this.getClass().getName(), "The result was empty!");
                return;
            }
            Log.d(this.getClass().getName(), "I received a picture!");

            try
            {
                inputStream = this.getContext().getContentResolver().openInputStream(data.getData());
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
            Bitmap avatarLarge = BitmapFactory.decodeStream(inputStream);

            // find out how 200 dp convert to actal pixels on this device
            Resources r = getResources();
            float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, r.getDisplayMetrics());
            int avatarLargeHeight = avatarLarge.getHeight();
            int avatarLargeWidth = avatarLarge.getWidth();
            // get the factor by which to resize the image, so that it fits in the defined frame
            float factor = 0.0f;
            if (avatarLargeHeight >= avatarLargeWidth)
            {
                factor = px / avatarLargeHeight;
            }
            else
            {
                factor = px / avatarLargeWidth;
            }
            int newHeight = (int)Math.round(avatarLargeHeight * factor);
            int newWidth = (int)Math.round(avatarLargeWidth * factor);

            Bitmap avatarSmall = Bitmap.createScaledBitmap(avatarLarge, newWidth, newHeight, true);

            ImageView ibPortrait = (ImageView)this.getView().findViewById(R.id.ibPortrait);
            ibPortrait.setImageBitmap(avatarSmall);
            imageChanged = true;
        }
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

    public void setBirthdate(int year, int month, int dayOfMonth)
    {
        TextView tvBirthdate = (TextView) getView().findViewById(R.id.tvBirthdate);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        tvBirthdate.setText(df.format(cal.getTime()));
    }

    private void validateInput()
    {
        // get references to the view elements
        Button btSave = (Button) getView().findViewById(R.id.btSave);
        Button btCancel = (Button) getView().findViewById(R.id.btCancel);
        final EditText etFirstName = (EditText)getView().findViewById(R.id.etFirstName);
        final EditText etLastName = (EditText)getView().findViewById(R.id.etLastName);
        final TextView tvBirthdate = (TextView)getView().findViewById(R.id.tvBirthdate);
        final RadioButton rbFemale = (RadioButton) getView().findViewById(R.id.rbFemale);
        final RadioButton rbMale = (RadioButton) getView().findViewById(R.id.rbMale);
        final ImageView ibPortrait = (ImageView) getView().findViewById(R.id.ibPortrait);

        // validate the input
        if ("".equals(etFirstName.getText().toString()) && "".equals(etLastName.getText().toString()))
        {
            showValidationMessage(R.string.ValidationMessageNameMissing);
            return;
        }
        if (!rbFemale.isChecked() && !rbMale.isChecked())
        {
            showValidationMessage(R.string.ValidationMessageNoSexSelected);
            return;
        }
        Date birthdate = null;
        try
        {
            birthdate = df.parse(tvBirthdate.getText().toString());
        }
        catch (ParseException e)
        {
            showValidationMessage(R.string.ValidationMessageDateWrong);
            return;
        }

        Child child = new Child();
        child.set_id(childId);
        child.setFirstName(etFirstName.getText().toString());
        child.setLastName(etLastName.getText().toString());
        Calendar cal = Calendar.getInstance();
        cal.setTime(birthdate);
        child.setBirthdateDayOfMonth(cal.get(Calendar.DAY_OF_MONTH));
        child.setBirthdateMonth(cal.get(Calendar.MONTH));
        child.setBirthdateYear(cal.get(Calendar.YEAR));
        if (rbFemale.isChecked())
        {
            child.setSex(Child.FEMALE);
        }
        else
        {
            child.setSex(Child.MALE);
        }

        try
        {
            if (imageChanged)
            {
                Drawable drawable = ibPortrait.getDrawable();
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                Bitmap portrait = bitmapDrawable.getBitmap();
                child.setPortrait(portrait);
            }
        }
        catch (ClassCastException e)
        {
            Log.d(this.getClass().getName(), "The image could not be converted into a bitmap.");
        }

        //TODO: Refactor, so that the method returns a boolean value
        listener.onSaveChildClicked(child);
    }
}
