package com.example.todo;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.todo.data.Contact;
import com.example.todo.datahandler.ContactsProvider;
import com.example.todo.data.ToDo;
import com.example.todo.datahandler.DataManager;
import com.example.todo.devices.LocationHandler;
import com.example.todo.media.ToDoAudioRecorder;
import com.example.todo.service.ToDoServiceBound;

import java.io.File;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AddNewActivity extends Activity {
    public static final int MODE_INSERT_TODO = 0;
    public static final int MODE_UPDATE_TODO = 1;

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;


    private DatePicker date;
    private static String TEST_TAG = "TestTag";

    ToDoServiceBound boundService;
    boolean mBound = false;

    boolean recording = false; //Audio Recording status
    private ToDoAudioRecorder audioRecorder;

    @Override
    protected void onStart(){
        super.onStart();
        //Bind to ToDoServiceBound
        Intent boundIntent = new Intent(this, ToDoServiceBound.class);
        bindService(boundIntent, mConnection, Context.BIND_AUTO_CREATE);

        LocationHandler.getInstance().setContext(this);
        LocationHandler.getInstance().startLocationListener();
    }

    @Override
    protected void onPause(){
        super.onPause();

        if (recording){
            Log.i(TEST_TAG, "onPause - Stop recording");
            audioRecorder.stopRecording();
            Button recordButton = (Button)findViewById(R.id.record_audio_button);
            recordButton.setText(R.string.startrec);
            recording = false;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        //Unbind from ToDoServiceBound
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }

        LocationHandler.getInstance().stopLocationListener();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager_main);

        long toDoId = getIntent().getLongExtra("id", 0);
        int mode = getIntent().getIntExtra("mode", MODE_INSERT_TODO);

        //Pager
        ToDoPagerAdapter tdpAdapter = new ToDoPagerAdapter();
        tdpAdapter.setToDoId(toDoId);
        tdpAdapter.setMode(mode);
        ViewPager mainPager = (ViewPager)findViewById(R.id.viewPagerMain);
        mainPager.setAdapter(tdpAdapter);
        mainPager.setCurrentItem(1);

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
    public void saveToDo(View view){
        long toDoId = getIntent().getLongExtra("id", 0);
        int mode = getIntent().getIntExtra("mode", MODE_INSERT_TODO);
        Log.i(TEST_TAG, "Save button pressed. Mode:" + mode + " toDoId:" + toDoId);

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

        TextView gpsLong = (TextView)findViewById(R.id.gps_long);
        TextView gpsLat = (TextView)findViewById(R.id.gps_lat);

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
        newToDo.setLatitude(gpsLat.getText().toString());
        newToDo.setLongitude(gpsLong.getText().toString());

        if (recording){
            Log.i(TEST_TAG, "saveToDo - Stop recording");
            audioRecorder.stopRecording();
            recording = false;
        }

        if (mode == MODE_INSERT_TODO) {
            //Add new ToDo
            toDoId = DataManager.getInstance().addTodo(newToDo);
            //Move the file from temp dir to the todo id's dir
            File audiofile = new File(DataManager.getToDoDir(-1) +
                    ToDoAudioRecorder.TODO_SOUND_FILE);
            audiofile.renameTo(new File(DataManager.getToDoDir(toDoId) +
                    ToDoAudioRecorder.TODO_SOUND_FILE));

        } else if (mode == MODE_UPDATE_TODO) {
            //Update ToDo
            newToDo.setId(toDoId);
            DataManager.getInstance().updateTodo(newToDo);

        }

        //play sound
        // http://www.beatmode.com/historical/free-loops/exciton-loops/mp3/exciton-136-02.mp3
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.sound);
        mediaPlayer.start();

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

    /**
     * Record Audio button clicked
     * Start or Stop audio recording
     * @param view
     */
    public void recordAudio(View view){
        Button recordButton = (Button)findViewById(R.id.record_audio_button);
        Button playButton = (Button)findViewById(R.id.play_audio_button);
        if (recording) {
            recordButton.setText(R.string.startrec);
            audioRecorder.stopRecording();
            playButton.setEnabled(true);
        }else {
            // disable play
            playButton.setEnabled(false);
            long toDoId = getIntent().getLongExtra("id", -1);
            String fileName = DataManager.getToDoDir(toDoId) + ToDoAudioRecorder.TODO_SOUND_FILE;
            audioRecorder = new ToDoAudioRecorder(fileName);
            audioRecorder.startRecording();
            recordButton.setText(R.string.stoprec);
        }
        recording = !recording;
    }

    /**
     * Play the existing audio file
     * @param view
     */
    public void playAudio(View view){
        try {
            long toDoId = getIntent().getLongExtra("id", -1);
            File af = new File(DataManager.getToDoDir(toDoId) + ToDoAudioRecorder.TODO_SOUND_FILE);
            if (af.exists()) {
                Uri myUri = Uri.fromFile(af);
                MediaPlayer mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setDataSource(getApplicationContext(), myUri);
                mediaPlayer.prepare();
                mediaPlayer.start();
            }
        } catch (Exception e) {
            Log.d(TEST_TAG, "Can not play", e);
        }

    }

    public void captureImage(View view){
        // create Intent to take a picture
        // and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        long toDoId = getIntent().getLongExtra("id", -1);
        File ifile = new File(DataManager.getToDoDir(toDoId) + "image.jpg");
        Uri fileUri = Uri.fromFile(ifile); // create a file to save the image
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name

        // start the image capture Intent
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        long toDoId = getIntent().getLongExtra("id", -1);
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Image captured and saved to fileUri specified in the Intent
                Toast.makeText(this, "Image saved to:\n" +
                        DataManager.getToDoDir(toDoId) + "image.jpg", Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                // User cancelled the image capture
            } else {
                // Image capture failed, advise user
            }
        }

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
