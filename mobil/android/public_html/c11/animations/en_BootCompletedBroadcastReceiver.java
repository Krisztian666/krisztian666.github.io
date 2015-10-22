package com.example.todo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.todo.service.ToDoServiceBound;

public class BootCompletedBroadcastReceiver extends BroadcastReceiver {
    public BootCompletedBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        / This method is called when the 
        / BroadcastReceiver is receiving an Intent broadcast.

        Toast.makeText(context, "ToDoService STARTING", //{img:"scr-main-servicestart.png", speak:"Show toast."}
                Toast.LENGTH_SHORT).show();

        Intent startIntent = new Intent(context, ToDoServiceBound.class);
        startIntent.setAction("");
        context.startService(startIntent);//{speak:"Start service."}

        Toast.makeText(context, "ToDoService STARTED", 
                Toast.LENGTH_LONG).show();

    }
}
