package com.example.todo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class ToDoReceiver extends BroadcastReceiver {
    public ToDoReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.i("ToDoReceiver","New ToDO: " +
                " name: " + intent.getStringExtra("name") +
                " datetime: " + intent.getLongExtra("datetime", 0) + " ms" +
                " allday: " + intent.getBooleanExtra("allday", false) +
                " priority: " + intent.getStringExtra("priority") +
                " description: " + intent.getStringExtra("description") +
                " url: " + intent.getStringExtra("url"));

        //log the date
        Calendar calEvent = Calendar.getInstance();
        calEvent.setTimeInMillis(intent.getLongExtra("datetime", 0));
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String formattedDate = dateFormat.format(calEvent.getTime());
        Log.i("ToDoReceiver", "FormattedDateTime:" + formattedDate);
    }
}
