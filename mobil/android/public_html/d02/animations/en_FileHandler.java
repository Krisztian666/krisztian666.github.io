package com.example.todo.data;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Read and write ToDo data to a file
 */
public class FileHandler {
private static final String TEST_TAG = "FileHandler";
private static final String TODO_FILE = "todo";//{speak:"File name"}
private static final String SEPARATOR = "#;#"; / /only for demonstration
/*
File format example:
"            
Todo1FromString#;#LOW#;#1446337020170#;#true#;#Description1#;#http:/ /android.com
Todo2FromString#;#MED#;#1446337080170#;#false#;#Description2#;#http:/ /android.com 
"
This this is only for demonstration! 
Later we will learn better solutions as well.            
*/            

/**
 * Read the list from file
 * @param context
 * @return
 */
public static ArrayList<ToDo> readToDoList(Context context){
    Log.i(TEST_TAG, "Reading file...");
    ArrayList<ToDo> list = new ArrayList<ToDo>();
    try {
        BufferedReader inputReader = new BufferedReader(
                new InputStreamReader(
                        context.openFileInput(TODO_FILE)));//{speak:"Open the file for reading"}
        String line;
        while ((line = inputReader.readLine()) != null) {//{speak:"Read the file line by line"}

            Log.i(TEST_TAG, line);
            list.add(toDoFromString(line));//{speak:"Create ToDo list."}

        }
    } catch (IOException e) {
        Log.i(TEST_TAG, "The file does not exist. " + e.getMessage());
    } catch (NullPointerException ne) {
        Log.i(TEST_TAG, "The file does not exist. " + ne.getMessage());
    }

    return list;
}

/**
 * Write list to file
 * @param context
 * @param list
 */
public static void writeToDoList(Context context, ArrayList<ToDo> list){
    Log.i(TEST_TAG, "Writing file...");
    String line;
    FileOutputStream outputStream;
    try {
        if (!list.isEmpty()) {
            outputStream = 
                context.openFileOutput(TODO_FILE, Context.MODE_PRIVATE);//{speak:"Open the file for writing"}
            for (ToDo td:list) {
                line = toDoToString(td);
                / /write one line containing ToDo data to the file.
                outputStream.write(line.getBytes());//{speak:"Write ToDo lines"}
            }
            outputStream.close();//{speak:"Close the stream"}
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

private static ToDo toDoFromString(String line){//{speak:"Create ToDo object"}
    String[] std = line.split(SEPARATOR);
    Log.i(TEST_TAG, "size:"+std.length);
    boolean allday = false;
    if ("true".equals(std[3])){
        allday = true;
    }
    ToDo.Priority pr = ToDo.Priority.HIGH;
    if ("LOW".equals(std[1])){
        pr = ToDo.Priority.LOW;
    } else if ("MED".equals(std[1])){
        pr = ToDo.Priority.MED;
    }
    ToDo td = new ToDo(std[0].trim(), pr, Long.parseLong(std[2]), allday, std[4].trim(), std[5].trim());

    return td;
}

private static String toDoToString(ToDo td){//{speak:"Write ToDo to string"}
    return  td.getName() + " " + SEPARATOR +
            td.getPriority() + SEPARATOR +
            td.getDate() + SEPARATOR +
            td.isAllDay() + SEPARATOR +
            td.getDescription() + " " + SEPARATOR +
            td.getUrl() + " " + "\n";
}
}
