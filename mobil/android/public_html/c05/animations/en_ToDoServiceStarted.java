package com.example.todo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.todo.data.DataManager;
import com.example.todo.data.ToDo;


public class ToDoServiceStarted extends Service {
    private static String SERVICE_TAG = "StartedService";

    public ToDoServiceStarted() {
    }

    @Override
    public void onCreate() {
        Log.i(SERVICE_TAG, "init the service");
        super.onCreate();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(SERVICE_TAG, "starting the service. startId:" + startId);

        Log.i(SERVICE_TAG,"New ToDO: " +
                " name: " + intent.getStringExtra("name") +
                " datetime: " + intent.getLongExtra("datetime", 0) + " ms" +
                " allday: " + intent.getBooleanExtra("allday", false) +
                " priority: " + intent.getStringExtra("priority") +
                " description: " + intent.getStringExtra("description") +
                " url: " + intent.getStringExtra("url"));


        ToDo.Priority pPriority = ToDo.Priority.LOW;
        switch (intent.getStringExtra("priority")){
            case "LOW": pPriority = ToDo.Priority.LOW;
                break;
            case "MED": pPriority = ToDo.Priority.MED;
                break;
            case "HI": pPriority = ToDo.Priority.HIGH;
                break;
        }

        /Add new ToDo
        DataManager.getInstance().addTodo(new ToDo(
                intent.getStringExtra("name"),
                pPriority,
                intent.getLongExtra("datetime", 0),
                intent.getBooleanExtra("allday", false),
                intent.getStringExtra("description"),
                intent.getStringExtra("url")));

        /stop the service
        stopSelf(startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(SERVICE_TAG, "destroy the service");
        super.onDestroy();
    }



    @Override
    public IBinder onBind(Intent intent) {
        / TODO: Return the communication channel to the service.
        / This is not a Bound Service, return null.
        return null;
    }
}
