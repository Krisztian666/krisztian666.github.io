package com.example.todo;

import android.app.ActionBar;
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
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.ContextMenu;
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
import android.widget.Toast;

import com.example.todo.data.ToDo;
import com.example.todo.datahandler.DataManager;
import com.example.todo.telephon.ToDoPhoneStateListener;


public class MainActivity extends ListActivity {
    private static String TAG = "MainActivity";
    final Activity activity = this;
    private ImageButton button;
    private TextView about;

    private ListView navBarDrawerList;
    private ArrayAdapter<String> navBarAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i(TAG, "Creating MainActivity...");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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


        //Action Bar
        ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(true);

        DataManager.getInstance().setContext(getApplicationContext());
        ToDoAdapter toDoAdapter = new ToDoAdapter(getApplicationContext(), DataManager.getInstance().getToDoList());
        setListAdapter(toDoAdapter);

        //register menu for the listed items
        registerForContextMenu(getListView());

        // check phone state
        TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        tm.listen(new ToDoPhoneStateListener(getApplicationContext()),
                PhoneStateListener.LISTEN_CALL_STATE );
        Toast.makeText(getApplicationContext(), "# " + tm.getLine1Number() + " roaming:" + tm.isNetworkRoaming(), Toast.LENGTH_SHORT).show();

    }

    private void showAboutDialog(){
        Log.i(TAG, "Creating About AlertDialog...");
        AlertDialog.Builder builder =new AlertDialog.Builder(activity);
        builder.setMessage(R.string.about_txt);
        builder.setTitle(R.string.about);
        builder.setNeutralButton(R.string.close, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i(TAG, "About AlertDialog Button clicked.");
            }
        });
        builder.show();
        createNotification(1, getResources().getString(R.string.about), getResources().getString(R.string.about_called));
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
        String[] menuArray = { getResources().getString(R.string.add_new_todo),
                getResources().getString(R.string.action_settings),
                getResources().getString(R.string.simport),
                getResources().getString(R.string.about) };
        navBarAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuArray);
        navBarDrawerList.setAdapter(navBarAdapter);
        navBarDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "Navigation Bar List item selected: " + position);
                switch (position) {
                    case 0:
                        Intent myIntent = new Intent();
                        myIntent.setClassName("com.example.todo", "com.example.todo.AddNewActivity");
                        startActivity(myIntent);
                        break;
                    case 1:
                        break;
                    case 2:
                        Intent iIntent = new Intent();
                        iIntent.setClassName("com.example.todo", "com.example.todo.ImportActivity");
                        startActivity(iIntent);
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
        ((ToDoAdapter)getListAdapter()).setList(DataManager.getInstance().getToDoList());
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
                Log.i(TAG, "Settings menu selected");
                Intent myIntentS = new Intent();
                myIntentS.setClassName("com.example.todo", "com.example.todo.ConfigureActivity");
                startActivity(myIntentS);
                return true;
            case R.id.action_import:
                Log.i(TAG, "Import menu selected");
                Intent myIntentI = new Intent();
                myIntentI.setClassName("com.example.todo", "com.example.todo.ImportActivity");
                startActivity(myIntentI);
                return true;
            case R.id.action_about:
                showAboutDialog();
                return true;
            case android.R.id.home:
                Log.i(TAG, "ActionBar Home selected");
                return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        if (view.equals(getListView())){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
            menu.setHeaderTitle(((ToDo) getListAdapter().getItem(info.position)).getName());
            menu.add(Menu.NONE, 0, 0, getResources().getString(R.string.detailed_todo));
            menu.add(Menu.NONE, 1, 1, getResources().getString(R.string.delete));
            menu.add(Menu.NONE, 2, 2, getResources().getString(R.string.back));
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int menuItemIndex = item.getItemId();
        if (menuItemIndex ==0) {
            Intent myIntent = new Intent();
            myIntent.setClassName("com.example.todo", "com.example.todo.AddNewActivity");
            myIntent.putExtra("id", ((ToDo) getListAdapter().getItem(info.position)).getId());
            myIntent.putExtra("mode", AddNewActivity.MODE_UPDATE_TODO);
            startActivity(myIntent);
        }
        if (menuItemIndex == 1){
            Log.i(TAG, "Delete item");
            DataManager.getInstance().deleteTodo(((ToDo) getListAdapter().getItem(info.position)).getId());
            ((ToDoAdapter)getListAdapter()).deleteRow(info.position);
            ((ToDoAdapter)getListAdapter()).notifyDataSetChanged();
        }
        return true;
    }

}
