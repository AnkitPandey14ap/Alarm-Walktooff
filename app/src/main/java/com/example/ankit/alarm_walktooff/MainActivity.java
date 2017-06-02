package com.example.ankit.alarm_walktooff;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Button startButton;
    private Button stopButton;
    private Button startAtButton;
    private PendingIntent pendingIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        stopButton = (Button) findViewById(R.id.stopButton);
        startAtButton = (Button) findViewById(R.id.startAtButton);

        //It will perform  a broadcast
/*
        Intent alarmIntent=new Intent(this, MainActivity.class);
        pendingIntent = PendingIntent.getActivity(this, 0, alarmIntent, 0);
*/

        Intent intent = new Intent(this, MainActivity.class);
        intent.setAction(Intent.ACTION_MAIN);
        //intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        pendingIntent = PendingIntent.getActivity(this, 0,
                intent, 0);




        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop();
            }
        });

        startAtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAt();
            }
        });

    }
    private void start() {



/*

        int alarmType = AlarmManager.ELAPSED_REALTIME;
        final int FIFTEEN_SEC_MILLIS = 1000;

        AlarmManager alarmManager = (AlarmManager)
                this.getSystemService(this.ALARM_SERVICE);

        alarmManager.setRepeating(alarmType, SystemClock.elapsedRealtime() + FIFTEEN_SEC_MILLIS,
                FIFTEEN_SEC_MILLIS, pendingIntent);


        Log.i("Ankit", "set");
        Toast.makeText(this, "Alarm set", Toast.LENGTH_SHORT).show();
*/




        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 5000;

        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),interval,pendingIntent);

        Log.i("Ankit", "set");
        Toast.makeText(this, "Alarm set", Toast.LENGTH_SHORT).show();

    }

    private void stop() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
        Toast.makeText(this, "Alarm canceled", Toast.LENGTH_SHORT).show();
    }

    public void startAt() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 1000 * 60 * 20;

        /* Set the alarm to start at 10:30 AM */
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 4);
        calendar.set(Calendar.MINUTE, 23);

        /* Repeating on every 20 minutes interval */
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                1000 * 60 * 20, pendingIntent);
    }



}
