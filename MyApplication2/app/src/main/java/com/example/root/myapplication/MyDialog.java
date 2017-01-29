package com.example.root.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by root on 1/10/17.
 */

public class MyDialog extends DialogFragment {
    public Callback callback;

    public MyDialog(){}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final double lat = getArguments().getDouble("LAT");
        final double lon = getArguments().getDouble("LONG");
        builder.setTitle(R.string.pick_color)
                .setItems(R.array.colors_array, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Bundle bundle = new Bundle();
                        bundle.putDouble("LAT", lat);
                        bundle.putDouble("LONG", lon);
                        callback.onDialogButtonClick("MyDialog", which, bundle);
                    }
                });
        return builder.create();

    }

    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View content = inflater.inflate(R.layout.mydialog, null);
        return content;
    }*/

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callback = (Callback) activity;
        }catch(ClassCastException e){
            callback = null;
        }
    }

    public static interface Callback {
        public void onDialogButtonClick(String string, int which, Bundle bundle);
    }
}

