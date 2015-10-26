package com.example.todo.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.example.todo.AddNewActivity;
import com.example.todo.R;
import com.example.todo.datahandler.DataManager;
import com.example.todo.data.ToDo;

public class ToDoServiceBound extends Service {
    private static String SERVICE_TAG = "BoundService";
    public ToDoServiceBound() {
    }

    @Override
    public void onCreate() {
        // Run the service in the Foregroud
        Intent notificationIntent = new Intent(this, AddNewActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
        builder.setSmallIcon(R.drawable.todoicon);
        builder.setContentTitle(getText(R.string.add_new_notification_title));
        builder.setContentText(getText(R.string.add_new_notification_message));
        builder.setContentIntent(pendingIntent);
        Notification notification = builder.build();

        final int notificationId = 123456;
        startForeground(notificationId, notification);
    }

    public class ToDoServiceBinder extends Binder {
        public ToDoServiceBound getService() {
            return ToDoServiceBound.this;
        }
    }

    private final IBinder binder = new ToDoServiceBinder();

    @Override
    public IBinder onBind(Intent intent) {
        // Return the communication channel to the service.
        return binder;
    }

    // method for client call

    public void addToDo(ToDo newToDo){
        Log.i(SERVICE_TAG, "addToDo called");

        //Add new ToDo
        DataManager.getInstance().addTodo(newToDo);
    }
}
