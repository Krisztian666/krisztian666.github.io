package com.example.todo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.app.NotificationCompat.Builder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.todo.data.ToDo;

import java.util.ArrayList;



public class MainActivity extends ListActivity {
    final Activity activity = this;
    private ImageButton button;
    private TextView about;

    private ListView navBarDrawerList;
    private ArrayAdapter<String> navBarAdapter;


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
                showAboutDialog();
            }
        });

        //Animation
        Animation pushAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_rotate);
        about.startAnimation(pushAnim);



        //Navigation Bar
        navBarDrawerList = (ListView)findViewById(R.id.navBarList);
        addDrawerListItems();




        ArrayList<ToDo> todoList = new ArrayList<ToDo>();
        todoList.add(new ToDo("Todo 1", ToDo.Priority.HIGH, "2015.09.01", true, "Description 1"));
        todoList.add(new ToDo("Todo 2", ToDo.Priority.LOW, "2015.09.02", true, "Description 2"));
        todoList.add(new ToDo("Todo 3", ToDo.Priority.MED, "2015.09.03", true, "Description 3"));


        ToDoAdapter toDoAdapter = new ToDoAdapter(getApplicationContext(),todoList);
        setListAdapter(toDoAdapter);
    }

    private void showAboutDialog(){
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
        createNotification(1, "About", "About is called...");
    }

    /**
     * Send notification to Status Bar
     * @param pNotificationId
     * @param pTitle
     * @param pText
     */
    private void createNotification(int pNotificationId, String pTitle, String pText) {
        NotificationCompat.Builder builder = new Builder(getApplicationContext());
        builder.setSmallIcon(R.drawable.todoicon);
        builder.setContentTitle(pTitle);
        builder.setContentText(pText);
        Notification notification = builder.build();

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(pNotificationId, notification);

    }

    /**
     * Create Navigation Bar menu
     */
    private void addDrawerListItems() {
        String[] menuArray = { "Add New ToDo", "Display Settings", "System Settings", "About" };
        navBarAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuArray);
        navBarDrawerList.setAdapter(navBarAdapter);
        navBarDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("TestTag", "Navigation Bar List item selected: " + position);
                switch (position) {
                    case 0:
                        Intent myIntent = new Intent();
                        myIntent.setClassName("com.example.todo", "com.example.todo.AddNewActivity");
                        startActivity(myIntent);
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        showAboutDialog();
                        break;
                }

            }
        });
    }

    @Override
     public void onWindowFocusChanged(boolean r) {
        super.onWindowFocusChanged(r);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            case R.id.action_addnew:
                Intent myIntent = new Intent();
                myIntent.setClassName("com.example.todo", "com.example.todo.AddNewActivity");
                startActivity(myIntent);
                return true;
            case R.id.action_settings:
                Log.i("TestTag", "Settings menu selected");
                return true;
            case R.id.action_settings_display:
                Log.i("TestTag", "Display Settings menu selected");
                return true;
            case R.id.action_settings_system:
                Log.i("TestTag", "System Settings menu selected");
                return true;
            case R.id.action_about:
                showAboutDialog();
                return true;
        }


        return super.onOptionsItemSelected(item);
    }

}
