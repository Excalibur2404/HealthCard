package com.example.carstenzimmermann.healthcard.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;

/**
 * Created by Carsten Zimmermann on 10.11.2016.
 */

public class ConfirmationDialogFragment extends AppCompatDialogFragment
{
    public static String KEY_TITLE_RESOURCE_ID = "title_resource_id";
    public static String KEY_MESSAGE_RESOURCE_ID = "message_resource_id";
    public static String KEY_BUTTON_TEXT_RESOURCE_ID = "button_text";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        Bundle args = getArguments();
        int titleResourceId = args.getInt(KEY_TITLE_RESOURCE_ID);
        int messageResourceId = args.getInt(KEY_MESSAGE_RESOURCE_ID);
        int buttonTextResourceId = args.getInt(KEY_BUTTON_TEXT_RESOURCE_ID);

        //TODO:Validate the given arguments

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setTitle(titleResourceId);
        dialogBuilder.setMessage(messageResourceId);
        dialogBuilder.setPositiveButton(buttonTextResourceId, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                //TODO: Aufruf einer Methode in der Activity, um eine Löschoperation auszuführen
                dialog.dismiss();
            }
        });
        return dialogBuilder.create();
    }
}
