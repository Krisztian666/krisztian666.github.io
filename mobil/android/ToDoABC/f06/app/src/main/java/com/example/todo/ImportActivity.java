package com.example.todo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todo.data.ToDo;
import com.example.todo.datahandler.DataManager;
import com.example.todo.datahandler.ImportToDoTask;
import com.example.todo.datahandler.json.ToDoJSONParser;
import com.example.todo.datahandler.xml.ToDoXmlPullParser;

import java.io.InputStream;

public class ImportActivity extends Activity {
    private static String TAG = "ImportActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_import, menu);
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

    public void importFromHttp(View view){

        EditText purl = (EditText)findViewById(R.id.importUrlEditText);
        String url = purl.getText().toString();
        if ("".equals(url.trim())){
            Toast toast = Toast.makeText(view.getContext(), view.getContext().getText(R.string.fillin), Toast.LENGTH_SHORT);
            toast.show();
        } else {
            if (!url.startsWith("http")) {
                url = "http://" + url;
            }
            Log.i(TAG, "Import from http: " + url);
            Object[] params = new Object[]{ImportToDoTask.MODE_HTTP_URL_CONN, url};
            new ImportToDoTask(getApplicationContext()).execute(params);
        }
    }

    public void importFromXml(View view){
        try {
            Log.i(TAG, "Read todos.xml from assets.");
            InputStream is = getAssets().open("todos.xml");
            for (ToDo todo: ToDoXmlPullParser.ParseXml(is)){
                DataManager.getInstance().addTodo(todo);
            }
            is.close();
            Toast toast = Toast.makeText(view.getContext(), view.getContext().getText(R.string.import_true), Toast.LENGTH_SHORT);
            toast.show();
        } catch (Exception e) {
            Toast toast = Toast.makeText(view.getContext(), view.getContext().getText(R.string.import_false), Toast.LENGTH_SHORT);
            toast.show();
            e.printStackTrace();
        }
    }

    public void importFromJSON(View view){
        try {
            Log.i(TAG, "Read todos.json from assets.");
            InputStream is = getAssets().open("todos.json");
            for (ToDo todo: ToDoJSONParser.ParseJSON(is)){
                DataManager.getInstance().addTodo(todo);
            }
            is.close();
            Toast toast = Toast.makeText(view.getContext(), view.getContext().getText(R.string.import_true), Toast.LENGTH_SHORT);
            toast.show();
        } catch (Exception e) {
            Toast toast = Toast.makeText(view.getContext(), view.getContext().getText(R.string.import_false), Toast.LENGTH_SHORT);
            toast.show();
            e.printStackTrace();
        }
    }

}
