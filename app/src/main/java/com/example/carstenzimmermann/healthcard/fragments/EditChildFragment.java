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
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
    static final String CHILD = "child";
    private DateFormat df;
    EditChildFragmentListener listener;
    public static String EDIT_CHILD_FRAGMENT_LISTENER = "listener";
    public static final String TASK_NEW_CHILD = "new_child";
    public static final String TASK_EDIT_CHILD = "edit_child";
    public static final String KEY_TASK = "task";
    private int childId;
    private String task;
    private static int PICK_PHOTO_FOR_AVATAR = 1;
    private boolean imageChanged;

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
        final EditText etFirstName = (EditText)view.findViewById(R.id.etFirstName);
        final EditText etLastName = (EditText)view.findViewById(R.id.etLastName);
        final TextView tvBirthdate = (TextView)view.findViewById(R.id.tvBirthdate);
        final RadioButton rbFemale = (RadioButton) view.findViewById(R.id.rbFemale);
        final RadioButton rbMale = (RadioButton) view.findViewById(R.id.rbMale);
        final ImageView ibPortrait = (ImageView) view.findViewById(R.id.ibPortrait);

        Bundle bundle = this.getArguments();
        task = bundle.getString(KEY_TASK);
        if (TASK_EDIT_CHILD.equals(task))
        {
            //TODO: Offer a UI to crop the image by user preferences
            if (bundle != null)
            {
                childId = bundle.getInt(Child.KEY_ID);
                etFirstName.setText(bundle.getString(Child.KEY_FIRST_NAME));
                etLastName.setText(bundle.getString(Child.KEY_LAST_NAME));
                int birthdateDayOfMonth = bundle.getInt(Child.KEY_BIRTHDATE_DAY_OF_MONTH);
                int birthdateMonth = bundle.getInt(Child.KEY_BIRTHDATE_MONTH);
                int birthdateYear = bundle.getInt(Child.KEY_BIRTHDATE_YEAR);

                Calendar cal = Calendar.getInstance();
                if (birthdateDayOfMonth == 0 || birthdateMonth == 0 || birthdateYear == 0)
                {

                    cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
                    cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
                    cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH));

                }
                else
                {
                    cal.set(Calendar.YEAR, birthdateYear);
                    cal.set(Calendar.MONTH, birthdateMonth);
                    cal.set(Calendar.DAY_OF_MONTH, birthdateDayOfMonth);
                    tvBirthdate.setText(df.format(cal.getTime()));
                }

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
                Parcelable portraitParcel = bundle.getParcelable(Child.KEY_PORTRAIT);
                Bitmap portrait = null;
                if (portraitParcel != null)
                {
                    try
                    {
                        portrait = (Bitmap)portraitParcel;
                        ibPortrait.setImageBitmap(portrait);
                    }
                    catch (ClassCastException e)
                    {
                        Log.d(this.getClass().getName(), "Could not cast an image to a Bitmap.");
                    }
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

        ibPortrait.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                pickImage();
            }
        });

        return view;
    }

    public interface EditChildFragmentListener
    {
        public void onSaveChildClicked(Child child);
        public void onCancelClicked();
    }

    private void pickImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_PHOTO_FOR_AVATAR);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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
}
