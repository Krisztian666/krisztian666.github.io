package com.example.todo.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.example.todo.data.DataManager;
import com.example.todo.data.ToDo;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class ToDoServiceIntent extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String ACTION_ADD_TODO= "com.example.todo.service.action.AddToDo";
    public static final String ACTION_BAZ = "com.example.todo.service.action.BAZ";

    private static final String SERVICE_TAG = "IntentService";


    public ToDoServiceIntent() {
        super("ToDoServiceIntent");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD_TODO.equals(action)) {
                Log.i(SERVICE_TAG, "ACTION_ADD_TODO");

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

                //Add new ToDo
                DataManager.getInstance().addTodo(new ToDo(
                        intent.getStringExtra("name"),
                        pPriority,
                        intent.getLongExtra("datetime", 0),
                        intent.getBooleanExtra("allday", false),
                        intent.getStringExtra("description"),
                        intent.getStringExtra("url")));


            } else if (ACTION_BAZ.equals(action)) {
                // hadle ACTION_BAZ
            }
        }
    }

}
