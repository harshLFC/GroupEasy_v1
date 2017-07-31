package com.example.lenovo.groupeasy_v1;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by lenovo on 13-07-2017.
 */

public class PollDialog extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.new_poll,null);

        DialogInterface.OnClickListener listner = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Log.i("TAG", "Wahbdahdias");
            }
        };

        return new AlertDialog.Builder(getActivity())
                .setTitle("Create Poll")
                .setView(v)
                .setPositiveButton(android.R.string.ok, listner)
                .create();
    }
}
