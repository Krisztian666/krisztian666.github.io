package com.example.todo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.todo.data.DataManager;
import com.example.todo.data.ToDo;
import com.example.todo.service.ToDoServiceStarted;

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

        ToDo.Priority pPriority = ToDo.Priority.LOW;
        switch (intent.getStringExtra("priority")){
            case "LOW": pPriority = ToDo.Priority.LOW;
                break;
            case "MED": pPriority = ToDo.Priority.MED;
                break;
            case "HI": pPriority = ToDo.Priority.HIGH;
                break;
        }


        //add ToDo
        DataManager.getInstance().addTodo(new ToDo(
                intent.getStringExtra("name"),
                pPriority,
                intent.getLongExtra("datetime", 0),
                intent.getBooleanExtra("allday", false),
                intent.getStringExtra("description"),
                intent.getStringExtra("url")));

    }
}
