package com.example.todo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

public class ConfigureActivity extends Activity {
    private static final String TAG = "ConfigureTag";
    public static final String PREF_H24 = "h24";
    public static final String PREF_FILE = "com.example.todo.PREFERENCE_FILE_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_configure, menu);

        SharedPreferences sharedPref = getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        boolean defaultValue = true;
        boolean bh24 = sharedPref.getBoolean(PREF_H24, defaultValue);
        CheckBox h24 = (CheckBox)findViewById(R.id.h24checkBox);
        h24.setChecked(bh24);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void configureSaved(View view){
        Log.i(TAG, "Add button pressed..");

        // Set the time format of TimePicker in AddNewActivity/ToDoPagerAdapter
        CheckBox h24 = (CheckBox)findViewById(R.id.h24checkBox);

        SharedPreferences sharedPref = getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(PREF_H24, h24.isChecked());
        editor.commit();

        // Close this activity, return to MainActivity
        setResult(RESULT_OK);
        finish();
    }
}
