package com.example.todo.datahandler.net.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;
import com.example.todo.data.ToDo;
import com.example.todo.datahandler.FileHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * HttpURLConnectionHandler
 */
public class HttpURLConnectionHandler {

private static final String TAG = "HttpURLConnectionH";

/**
 * Read todos from an url.
 * @param pUrl
 * @return
 * @throws IOException
 */
public static ArrayList<ToDo> readStreamFrom(String pUrl) throws Exception{
    Log.d(TAG, "Reading from url: " + pUrl);
    ArrayList<ToDo> list = null;
    URL url = new URL(pUrl);
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    try {
        list = readStream(con.getInputStream());
        Log.d(TAG, "Returned todos: " + list.size());
    } finally {
        con.disconnect();
    }
    return list;
}

/**
 * Read the stream and convert to ToDo
 * @param in
 * @return
 */
private static ArrayList<ToDo>  readStream(InputStream in) throws Exception{
    ArrayList<ToDo> list = new ArrayList<ToDo>();
    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    try {
        String line = "";
        while ((line = reader.readLine()) != null) {
            list.add(FileHandler.toDoFromString(line));
        }
    } finally {
        reader.close();
    }
    return list;
}

/**
 * Check if connection is available.
 * @param context
 * @return
 */
public static boolean checkInternetConnection(Context context) {
    / / get Connectivity Manager
    ConnectivityManager connec =
            (ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE);

    / / Check for network connections
    if ( connec.getNetworkInfo(0).getState() == 
            android.net.NetworkInfo.State.CONNECTED ||
            connec.getNetworkInfo(0).getState() == 
            android.net.NetworkInfo.State.CONNECTING ||
            connec.getNetworkInfo(1).getState() == 
            android.net.NetworkInfo.State.CONNECTING ||
            connec.getNetworkInfo(1).getState() == 
            android.net.NetworkInfo.State.CONNECTED ) {
        Log.d(TAG, "Connected");
        return true;
    } else if ( connec.getNetworkInfo(0).getState() == 
            android.net.NetworkInfo.State.DISCONNECTED ||
                    connec.getNetworkInfo(1).getState() == 
            android.net.NetworkInfo.State.DISCONNECTED  ) {
        Log.d(TAG, "Not Connected ");
        return false;
    }
    return false;
}

}
