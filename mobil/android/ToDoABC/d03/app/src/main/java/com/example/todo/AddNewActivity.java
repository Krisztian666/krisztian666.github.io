package com.example.todo;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.todo.data.Contact;
import com.example.todo.datahandler.ContactsProvider;
import com.example.todo.data.ToDo;
import com.example.todo.datahandler.DataManager;
import com.example.todo.service.ToDoServiceBound;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AddNewActivity extends Activity {
    private DatePicker date;
    private static String TEST_TAG = "TestTag";

    ToDoServiceBound boundService;
    boolean mBound = false;

    @Override
    protected void onStart(){
        super.onStart();
        //Bind to ToDoServiceBound
        Intent boundIntent = new Intent(this, ToDoServiceBound.class);
        bindService(boundIntent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //Unbind from ToDoServiceBound
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }

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
                Log.i(TEST_TAG, "Settings menu selected");
                return true;
            case android.R.id.home:
                Log.i(TEST_TAG, "ActionBar Home selected");
                Intent myIntent = new Intent();
                myIntent.setClassName("com.example.todo", "com.example.todo.MainActivity");
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Add new toDo
     * @param view
     */
    public void addNewToDo(View view){
        Log.i(TEST_TAG, "Add button pressed..");

        EditText name = (EditText)findViewById(R.id.toDoNameEditText);
        EditText description = (EditText)findViewById(R.id.descriptionEditText);
        EditText url = (EditText)findViewById(R.id.urlEditText);
        CheckBox allDay = (CheckBox)findViewById(R.id.allDayCheckBox);

        RadioGroup priority = (RadioGroup)findViewById(R.id.priorityRadioGroup);
        String sPriority = "LOW";
        ToDo.Priority pPriority = ToDo.Priority.LOW;
        switch (priority.getCheckedRadioButtonId()){
            case R.id.pLowRadio: sPriority = "LOW";
                pPriority = ToDo.Priority.LOW;
                break;
            case R.id.pMedRadio: sPriority = "MED";
                pPriority = ToDo.Priority.MED;
                break;
            case R.id.pHighRadio: sPriority = "HI";
                pPriority = ToDo.Priority.HIGH;
                break;
        }
        DatePicker datePicker = (DatePicker)findViewById(R.id.date);
        TimePicker timePicker = (TimePicker)findViewById(R.id.time);

        //log the date
        Date date = new Date(datePicker.getCalendarView().getDate());
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = dateFormat.format(date);
        Log.i(TEST_TAG, formattedDate + " " + timePicker.getCurrentHour() + ":" + timePicker.getCurrentMinute());

        //convert the date and time to ms
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, datePicker.getYear());
        calendar.set(Calendar.MONTH, datePicker.getMonth());
        calendar.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
        calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
        calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
        calendar.set(Calendar.SECOND, 0);

        TextView contactName = (TextView)findViewById(R.id.contact_name);
        TextView contactPhone = (TextView)findViewById(R.id.contact_phone);

        /*
        // Add new broadcastIntent
        Intent intent = new Intent();
        intent.setAction("com.example.todo.ADD_NEW_TODO");

        intent.putExtra("name", name.getText().toString());
        intent.putExtra("priority", sPriority);
        intent.putExtra("datetime", calendar.getTimeInMillis());
        intent.putExtra("allday", allDay.isChecked());
        intent.putExtra("description", description.getText().toString());
        intent.putExtra("url", url.getText().toString());

        sendBroadcast(intent);
        */

        /*
        // call the started service to add new ToDo
        Intent sintent = new Intent(this, ToDoServiceStarted.class);
        sintent.putExtra("name", name.getText().toString());
        sintent.putExtra("priority", sPriority);
        sintent.putExtra("datetime", calendar.getTimeInMillis());
        sintent.putExtra("allday", allDay.isChecked());
        sintent.putExtra("description", description.getText().toString());
        sintent.putExtra("url", url.getText().toString());
        
        startService(sintent);
        */

        /*
        // call the IntentService to add new ToDo
        Intent isintent = new Intent(this, ToDoServiceIntent.class);
        isintent.setAction(ToDoServiceIntent.ACTION_ADD_TODO);
        isintent.putExtra("name", name.getText().toString());
        isintent.putExtra("priority", sPriority);
        isintent.putExtra("datetime", calendar.getTimeInMillis());
        isintent.putExtra("allday", allDay.isChecked());
        isintent.putExtra("description", description.getText().toString());
        isintent.putExtra("url", url.getText().toString());

        startService(isintent);
        */

        // call ToDoServiceBound
        //boundService.addToDo(newToDo);


        Contact contact = new Contact(contactName.getText().toString(),
                contactPhone.getText().toString(),
                "");
        ToDo newToDo = new ToDo(
                name.getText().toString(),
                pPriority,
                calendar.getTimeInMillis(),
                allDay.isChecked(),
                description.getText().toString(),
                url.getText().toString(),
                contact);

        //Add new ToDo
        DataManager.getInstance().addTodo(newToDo);




        // Close this activity, return to MainActivity
        setResult(RESULT_OK);
        finish();
    }

    /**
     * Show add contact dialog
     * @param view
     */
    public void addNewContactToToDo(View view){

        final ArrayList<Contact> contacts = ContactsProvider.getContacts(view.getContext());

        final Dialog dialog = new Dialog(view.getContext());
        dialog.setTitle(R.string.add_contact);
        dialog.setCancelable(true);

        ListView listView = new ListView(this);
        ToDoContactsAdapter toDoContactsAdapter = new ToDoContactsAdapter(view.getContext(), contacts);
        listView.setAdapter(toDoContactsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TEST_TAG, "item selected:" + position);
                TextView cName = (TextView)findViewById(R.id.contact_name);
                cName.setText(contacts.get(position).getContactName());
                TextView cPhone = (TextView)findViewById(R.id.contact_phone);
                cPhone.setText(contacts.get(position).getPhoneNumber());

                dialog.cancel();
            }
        });
        dialog.setContentView(listView);
        dialog.show();
    }



    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //bound to ToDoServiceBound
            ToDoServiceBound.ToDoServiceBinder binder = (ToDoServiceBound.ToDoServiceBinder) service;
            boundService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };

}
