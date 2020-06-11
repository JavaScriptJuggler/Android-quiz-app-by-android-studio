package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.RadialGradient;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Message;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

import static android.widget.Toast.LENGTH_SHORT;
import static com.example.test.R.drawable.alert;

public class MainActivity extends AppCompatActivity {


    Button bt1,bt2,bt3;
    RadioButton rd1,rd2,rd3,rd4;
    TextView tv,tv2,tv3;
    RadioGroup rg;
    Vibrator mVibrator;//for vibration
    private static String ans = "";
    int minute=0,second=0,i=-1,no=1;
    int[] array=new int[50];
    Cursor cursor;boolean review=true,playbause=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVibrator=(Vibrator)getSystemService(VIBRATOR_SERVICE);//getting vibrator service from system

        final messagebox msg=new messagebox(this);

        //components

        bt1= findViewById(R.id.button2);
        bt2= findViewById(R.id.button);
        bt3= findViewById(R.id.button3);
        rd1=findViewById(R.id.radioButton);
        rd2=findViewById(R.id.radioButton2);
        rd3=findViewById(R.id.radioButton3);
        rd4=findViewById(R.id.radioButton4);
        rg=findViewById(R.id.radioGroup);
        tv=findViewById(R.id.textView);
        tv2=findViewById(R.id.textView2);
        tv3=findViewById(R.id.textView3);



        //connection


        connection con=new connection(this);
        SQLiteDatabase db=con.getReadableDatabase();



       // connection from asset


        /*String path=this.getDatabasePath(connection1.dbname).getPath();
        SQLiteDatabase db=SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READWRITE);*/



         cursor=db.rawQuery("Select * from table1",null);

        if(cursor!=null)
        cursor.moveToFirst();

        i++;

        tv.setText(cursor.getString(1));
        rd1.setText(cursor.getString(2));
        rd2.setText(cursor.getString(3));
        rd3.setText(cursor.getString(4));
        rd4.setText(cursor.getString(5));
        ans=cursor.getString(6);

        tv3.setText("Question no -> "+no);



        //== compares the location of object where as .equals compare the value of object in android


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {



                    if (rd1.isChecked()) {
                        if (rd1.getText().equals(ans)) {
                            rd1.setBackgroundColor(Color.parseColor("#00a904"));
                        } else {
                            rd1.setBackgroundColor(Color.parseColor("#FF1302"));
                            mVibrator.vibrate(50);//vibrate for 50 milisecond

                            //


                        }

                        array[i] = 1;


                    } else if (rd2.isChecked()) {
                        if (rd2.getText().equals(ans)) {
                            rd2.setBackgroundColor(Color.parseColor("#00a904"));
                        } else {

                            rd2.setBackgroundColor(Color.parseColor("#FF1302"));
                            mVibrator.vibrate(50);


                            //

                        }
                        array[i] = 2;

                    } else if (rd3.isChecked()) {
                        if (rd3.getText().equals(ans)) {
                            rd3.setBackgroundColor(Color.parseColor("#00a904"));
                        } else {
                            rd3.setBackgroundColor(Color.parseColor("#FF1302"));
                            mVibrator.vibrate(50);

                            //

                        }
                        array[i] = 3;

                    } else if (rd4.isChecked()) {
                        if (rd4.getText().equals(ans)) {
                            rd4.setBackgroundColor(Color.parseColor("#00a904"));
                        } else {
                            rd4.setBackgroundColor(Color.parseColor("#FF1302"));
                            mVibrator.vibrate(50);
                            //


                        }
                        array[i] = 4;

                    } else {
                        rd2.setBackgroundColor(Color.TRANSPARENT);
                        rd3.setBackgroundColor(Color.TRANSPARENT);
                        rd1.setBackgroundColor(Color.TRANSPARENT);
                        rd4.setBackgroundColor(Color.TRANSPARENT);
                    }
            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                   if (cursor.moveToNext()) {


                       colorchnge();
                       i++;no++;
                       tv3.setText("Question no -> "+no);

                       setting(i);

                   } else {

                       cursor.moveToLast();
                       message("End of question", "Attention");
                       review = false;

                   }

               }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {

                    if (cursor.moveToPrevious()) {

                        colorchnge();
                        i--;no--;
                        tv3.setText("Question no -> "+no);
                        setting(i);

                    } else {
                       // Toast.makeText(MainActivity.this, "First Question", Toast.LENGTH_SHORT).show();
                        Toast toast=Toast.makeText(MainActivity.this,"First question", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL,0,0);
                        toast.setDuration(1000);
                        toast.show();

                        cursor.moveToFirst();
                    }
                }
        });








        final CountDownTimer timer=new CountDownTimer(1200000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                if(second!=60){
                    second++;
                }
                else
                {
                    second=0;
                    minute++;
                }

                tv2.setText("Time elapsed: "+minute+" : "+second);
            }

            @Override
            public void onFinish() {
               msg.closebox("Time's Up !!!","Warning","Submit");
            }
        }.start();





        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                msg.closebox("Sure to submit & exit ?","Warning","I'm Sure");

            }
        });

    }

    public void colorchnge()
    {
        rd2.setBackgroundColor(Color.TRANSPARENT);
        rd3.setBackgroundColor(Color.TRANSPARENT);
        rd1.setBackgroundColor(Color.TRANSPARENT);
        rd4.setBackgroundColor(Color.TRANSPARENT);


        rg.clearCheck();
    }

    public void message(String msg,String title)
    {

        new AlertDialog.Builder(this)
                .setMessage(msg)
                .setTitle(title)
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.finish();
                        System.exit(0);
                    }
                })
                .setNegativeButton("Restart", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        cursor.moveToFirst();
                        rg.clearCheck();
                        colorchnge();
                        Arrays.fill(array,0);//fill all element of array by 0
                        no=1;i=0;
                        tv3.setText("Question no -> "+no);
                       setting1();


                    }
                })
                .show();
    }



    public  void setting(int i)
    {

//colorchnge();
        tv.setText(cursor.getString(1));
        rd1.setText(cursor.getString(2));
        rd2.setText(cursor.getString(3));
        rd3.setText(cursor.getString(4));
        rd4.setText(cursor.getString(5));
        ans=cursor.getString(6);

        if (array[i] == 1) {
            rd1.setChecked(true);
        }
        else if (array[i] == 2) {
            rd2.setChecked(true);
        }
        else if (array[i] == 3) {
            rd3.setChecked(true);
        }
        else if (array[i] == 4) {
            rd4.setChecked(true);
        }


    }
    public void setting1()
    {
        tv.setText(cursor.getString(1));
        rd1.setText(cursor.getString(2));
        rd2.setText(cursor.getString(3));
        rd3.setText(cursor.getString(4));
        rd4.setText(cursor.getString(5));
        ans=cursor.getString(6);
    }








}
