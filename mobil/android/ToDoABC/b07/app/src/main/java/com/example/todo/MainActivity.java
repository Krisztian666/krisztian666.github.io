package com.example.todo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.todo.data.ToDo;

import java.util.ArrayList;


public class MainActivity extends ListActivity {
    final Activity activity = this;
    private ImageButton button;
    private TextView about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i("TestTag", "Creating MainActivity...");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (ImageButton)findViewById(R.id.addButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent();
                myIntent.setClassName("com.example.todo", "com.example.todo.AddNewActivity");
                startActivity(myIntent);
            }
        });

        about = (TextView)findViewById(R.id.aboutTewtView);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TestTag", "Creating About AlertDialog...");
                AlertDialog.Builder builder =new AlertDialog.Builder(activity);
                builder.setMessage("This is a demo ToDo application.");
                builder.setTitle("About");
                builder.setNeutralButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("TestTag", "About AlertDialog Button clicked.");
                    }
                });
                builder.show();
            }
        });

        ArrayList<ToDo> todoList = new ArrayList<ToDo>();
        todoList.add(new ToDo("Todo 1", ToDo.Priority.HIGH, "2015.09.01", true, "Description 1"));
        todoList.add(new ToDo("Todo 2", ToDo.Priority.LOW, "2015.09.02", true, "Description 2"));
        todoList.add(new ToDo("Todo 3", ToDo.Priority.MED, "2015.09.03", true, "Description 3"));


        ToDoAdapter toDoAdapter = new ToDoAdapter(getApplicationContext(),todoList);
        setListAdapter(toDoAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((ToDoAdapter)getListAdapter()).notifyDataSetChanged();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
