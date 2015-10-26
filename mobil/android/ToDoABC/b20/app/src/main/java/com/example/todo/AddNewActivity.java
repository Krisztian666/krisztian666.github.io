package com.example.todo;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
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
        setContentView(R.layout.view_pager_main);

        //Pager
        ToDoPagerAdapter tdpAdapter = new ToDoPagerAdapter();
        ViewPager mainPager = (ViewPager)findViewById(R.id.viewPagerMain);
        mainPager.setAdapter(tdpAdapter);
        mainPager.setCurrentItem(1);


//        Moved to ToDoPagerAdapter  instantiateItem method.
//
//        final Calendar today = Calendar.getInstance();
//        date = (DatePicker)findViewById(R.id.date);
//        date.setMinDate(today.getTimeInMillis() - 10000);

        //Action Bar
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_new, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                Log.i("TestTag", "Settings menu selected");
                return true;
            case android.R.id.home:
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
