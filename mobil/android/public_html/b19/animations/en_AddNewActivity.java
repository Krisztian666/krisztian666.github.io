package com.example.todo;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;

import java.util.Calendar;

public class AddNewActivity extends Activity {
    private DatePicker date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        final Calendar today = Calendar.getInstance();

        date = (DatePicker)findViewById(R.id.date);
        date.setMinDate(today.getTimeInMillis() - 10000);

        /Action Bar
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);//{speak:"Enable home button to go back"}

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_new, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                Log.i("TestTag", "Settings menu selected");
                return true;
            case android.R.id.home://{speak:"Go back to Mainactivity."}
                Log.i("TestTag", "ActionBar Home selected");
                Intent myIntent = new Intent();
                myIntent.setClassName("com.example.todo", "com.example.todo.MainActivity");
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
