package com.example.todo.datahandler;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.todo.R;
import com.example.todo.data.ToDo;
import com.example.todo.datahandler.net.http.HttpURLConnectionHandler;

import java.util.ArrayList;

/**
 * Import todos from different sources
 */
public class ImportToDoTask extends AsyncTask {
    
private static final String TAG = "ImportToDoTask";

public static final int MODE_HTTP_URL_CONN = 0;

private Context context;

public ImportToDoTask(Context pContext) {
    context = pContext;
}

/**
 * Override this method to perform a computation on a background thread. The
 * specified parameters are the parameters passed to {@link #execute}
 * by the caller of this task.
 * 
 * This method can call {@link #publishProgress} to publish updates
 * on the UI thread.
 *
 * @param params The parameters of the task.
 * @return A result, defined by the subclass of this task.
 * @see #onPreExecute()
 * @see #onPostExecute
 * @see #publishProgress
 */
@Override
protected Object doInBackground(Object[] params) {
    ArrayList<ToDo> newList = new ArrayList<ToDo>();
    / / import mode
    if (MODE_HTTP_URL_CONN == (int)params[0]) {
        try {
            if(HttpURLConnectionHandler.checkInternetConnection(context)){
                newList = HttpURLConnectionHandler.readStreamFrom(params[1].toString());
            } else {
                Log.i(TAG, "No connection.");
                return new Result(false, context.getString(R.string.no_connection));
            }
        } catch (Exception e){
            e.printStackTrace();
            return new Result(false, e.getMessage());
        }
    }

    for (ToDo td: newList) {
        DataManager.getInstance().addTodo(td);
    }
    return new Result(true, "");
}

@Override
public void onPostExecute(Object result){
    Result res = (Result)result;
    Log.i(TAG, "ok=" +result);
    String resultMsg = context.getString(R.string.import_true);
    if (!res.isSuccess()) {
        resultMsg = context.getString(R.string.import_false) + " " + res.getMessage();
    }

    Toast toast = Toast.makeText(context, resultMsg, Toast.LENGTH_SHORT);
    toast.show();
}

class Result {
    boolean success;
    String message;

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}

}
