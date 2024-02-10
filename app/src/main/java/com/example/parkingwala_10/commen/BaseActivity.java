package com.example.parkingwala_10.commen;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

public class BaseActivity {

    public static void SingleBtnShowAlertDialog(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Positive button click action, if needed
                        dialogInterface.dismiss();
                    }
                })
                .setCancelable(false);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
