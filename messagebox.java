package com.example.test;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;

public class messagebox extends AlertDialog {


    private final Context mycontext;

    protected messagebox(Context context) {
        super(context);


        this.mycontext=context;
    }
    public void closebox(String msg,String Title,String postext){
        new AlertDialog.Builder(mycontext)
                .setMessage(msg)
                .setTitle(Title)
                .setPositiveButton(postext, new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ((Activity)mycontext).finish();
                        System.exit(0);
                    }
                }).show();
    }
}

