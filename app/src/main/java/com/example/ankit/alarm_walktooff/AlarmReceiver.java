package com.example.ankit.alarm_walktooff;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by ankit on 3/6/17.
 */

class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //display a task when alarm get start
        Log.i("Ankit", "running");
        Toast.makeText(context, "running", Toast.LENGTH_SHORT).show();
    }
}
