package com.example.todo.datahandler.net.http;

import android.util.Log;

import com.example.todo.data.ToDo;
import com.example.todo.datahandler.FileHandler;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Example for the (deprecated) Apache HttpClient.
 * https://developer.android.com/about/versions/marshmallow/android-6.0-changes.html#behavior-apache-http-client
 */
public class HttpClientHandler {
    private static final String TAG = "HttpClientH";

    /**
     * Read todos from an url.
     * @param pUrl
     * @return
     * @throws IOException
     */
    public static ArrayList<ToDo> readStreamFrom(String pUrl) throws Exception{
        Log.d(TAG, "Reading from url: " + pUrl);
        ArrayList<ToDo> list = new ArrayList<ToDo>();

        InputStream inputStream = null;
        try {
            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(pUrl));
            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();
            // convert inputstream to ToDo list
            if(inputStream != null)
                list = readStream(inputStream);
        } finally {
            inputStream.close();
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
}
