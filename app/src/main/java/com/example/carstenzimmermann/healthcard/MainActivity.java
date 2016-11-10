package com.example.carstenzimmermann.healthcard;

import android.app.DatePickerDialog;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;

import com.example.carstenzimmermann.healthcard.entities.Child;
import com.example.carstenzimmermann.healthcard.fragments.ChildListFragment;
import com.example.carstenzimmermann.healthcard.fragments.DatePickerFragment;
import com.example.carstenzimmermann.healthcard.fragments.EditChildFragment;

public class MainActivity
        extends     AppCompatActivity
        implements  ChildListFragment.ChildListFragmentListener,
                    EditChildFragment.EditChildFragmentListener,
                    DatePickerDialog.OnDateSetListener
{
    DataManager dataManager;
    public final String CHILD_LIST_FRAGMENT_TAG = "child_list_fragment";
    public final String EDIT_CHILD_FRAGMENT_TAG = "edit_child_fragment";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        dataManager = DataManager.getInstance();
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        ChildListFragment childListFragment = (ChildListFragment)fm.findFragmentByTag(CHILD_LIST_FRAGMENT_TAG);
        if (childListFragment == null)
        {
            childListFragment = new ChildListFragment();
        }
        FragmentTransaction fta = fm.beginTransaction();
        fta.add(R.id.fragmentContainer, childListFragment, CHILD_LIST_FRAGMENT_TAG);
        fta.commit();
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

        /*
        Bundle arguments = new Bundle();
        arguments.putInt(Child.KEY_ID, child.get_id());
        arguments.putString(Child.KEY_FIRST_NAME, child.getFirstName());
        arguments.putString(Child.KEY_LAST_NAME, child.getLastName());
        arguments.putInt(Child.KEY_BIRTHDATE_YEAR, child.getBirthdateYear());
        arguments.putInt(Child.KEY_BIRTHDATE_MONTH, child.getBirthdateMonth());
        arguments.putInt(Child.KEY_BIRTHDATE_DAY_OF_MONTH, child.getBirthdateDayOfMonth());
        arguments.putInt(Child.KEY_SEX, child.getSex());
        arguments.putParcelable(Child.KEY_PORTRAIT, child.getPortrait());
        arguments.putString(EditChildFragment.KEY_TASK, EditChildFragment.TASK_EDIT_CHILD);
        editChildFragment.setArguments(arguments);*/

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
    public void onAddClicked()
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
    public void onDateEditClicked()
    {
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(datePickerFragment, "DATE_PICKER_FRAGMENT");
        transaction.commit();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
    {
        FragmentManager fm = getSupportFragmentManager();
        EditChildFragment editChildFragment = (EditChildFragment)fm.findFragmentByTag(EDIT_CHILD_FRAGMENT_TAG);
        if (editChildFragment != null)
        {
            editChildFragment.setBirthdate(year, month, dayOfMonth);
        }
    }
}
