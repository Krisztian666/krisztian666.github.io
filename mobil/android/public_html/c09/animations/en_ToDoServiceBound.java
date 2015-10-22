package com.example.todo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.example.todo.data.DataManager;
import com.example.todo.data.ToDo;

public class ToDoServiceBound extends Service {
    private static String SERVICE_TAG = "BoundService";
    public ToDoServiceBound() {
    }

    public class ToDoServiceBinder extends Binder {
        public ToDoServiceBound getService() {
            return ToDoServiceBound.this;
        }
    }

    private final IBinder binder = new ToDoServiceBinder();

    @Override
    public IBinder onBind(Intent intent) {
        / / Return the communication channel to the service.
        return binder;
    }

    / / method for client call

    public void addToDo(ToDo newToDo){
        Log.i(SERVICE_TAG, "addToDo called");

        / /Add new ToDo
        DataManager.getInstance().addTodo(newToDo);
    }
}
