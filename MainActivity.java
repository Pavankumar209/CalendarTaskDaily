package com.example.calendartask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity{

    Calendar c;
    CalendarView cv;
    Button b1;
    Button b2;
    TextView tv;
    String channelid="Mynotif1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        cv=findViewById(R.id.cv1);
        tv=findViewById(R.id.t1);

        createNotificationChannel();
        /*NotificationCompat.Builder nb = new NotificationCompat.Builder(this,channelid)
                .setSmallIcon(R.drawable.milk_photo)
                .setContentTitle("To be paid")
                .setContentText("The money to be paid is "+ money)*/

        b1.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,ListActivity.class);
                startActivity(i);
            }
        });

        b2.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i=new Intent(MainActivity.this,Show_Total.class);
                startActivity(i);
            }
        });
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView cv2, int year, int month, int day) {
                String date=day+"/"+(month+1)+"/"+year;
                if(day<10){
                    date="0"+date;
                }
                if(month<9){
                    date=date.substring(0,3)+"0"+date.substring(3);
                }
                Intent i=new Intent(MainActivity.this,SecondActivity.class);
                i.putExtra("date_selected",date);
                startActivity(i);
            }
        });


    }

    protected void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel nc = new NotificationChannel(channelid, "MyChannel1", NotificationManager.IMPORTANCE_DEFAULT);
            nc.setDescription("This channel is for calendar app created for ");

            NotificationManager nm = getSystemService(NotificationManager.class);
            nm.createNotificationChannel(nc);
        }
    }
}
