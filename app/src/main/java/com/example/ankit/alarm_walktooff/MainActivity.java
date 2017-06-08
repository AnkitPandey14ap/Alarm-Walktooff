package com.example.ankit.alarm_walktooff;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 20;
    private Button startButton;
    private Button stopButton;
    private Button startAtButton;
    private EditText hourEditText;
    private EditText minutesEditText;
    private PendingIntent pendingIntent;
    File folder;
    String song;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        stopButton = (Button) findViewById(R.id.stopButton);
        startAtButton = (Button) findViewById(R.id.startAtButton);





        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                Log.i("Ankit", "inner if");

            } else {
                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                Log.i("Ankit", "requsted READ PHONE_STATE");


            }
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.RECORD_AUDIO)) {

                Log.i("Ankit", "inner if");

            } else {
                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                Log.i("Ankit", "requsted READ PHONE_STATE");


            }
        }























        folder = new File(Environment.getExternalStorageDirectory() + "/"+"Empire.mp3");
        song=folder.toString();

        //It will perform  a broadcast
/*
        Intent alarmIntent=new Intent(this, MainActivity.class);
        pendingIntent = PendingIntent.getActivity(this, 0, alarmIntent, 0);
*/
        play(song);

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
        int interval = 2000;

        //manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),interval,pendingIntent);
        //manager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKE‌​UP, System.currentTimeMillis(), interval, pendingIntent);

        //manager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+interval,pendingIntent);
        Calendar futureDate = Calendar.getInstance();
        final long INTERVAL_SEVEN_SECONDS = 7 * 1000;
        manager.setRepeating(AlarmManager.RTC_WAKEUP, futureDate
                .getTime().getTime(), INTERVAL_SEVEN_SECONDS, pendingIntent);

        //manager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),pendingIntent);
        Log.i("Ankit", "set");
        Toast.makeText(this, "Alarm set", Toast.LENGTH_SHORT).show();

        play(song);

    }

    private void stop() {
            AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            manager.cancel(pendingIntent);
            Toast.makeText(this, "Alarm canceled", Toast.LENGTH_SHORT).show();
    }

    public void startAt() {
        hourEditText = (EditText) findViewById(R.id.hourEditText);
        minutesEditText = (EditText) findViewById(R.id.minutesEditText);

        int hh = Integer.parseInt(hourEditText.getText().toString());
        int mm = Integer.parseInt(minutesEditText.getText().toString());
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 1000 * 60 * 20;

        /* Set the alarm to start at 10:30 AM */
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hh);
        calendar.set(Calendar.MINUTE, mm);

        /* Repeating on every 20 minutes interval */
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                1000 * 60 * 20, pendingIntent);

    }
    void play(String mFileName){
        MediaPlayer mediaPlayer=new MediaPlayer();
        try {
            mediaPlayer.setDataSource(mFileName);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
