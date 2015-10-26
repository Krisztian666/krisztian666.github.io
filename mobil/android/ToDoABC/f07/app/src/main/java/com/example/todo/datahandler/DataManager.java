package com.example.todo.datahandler;

import android.content.Context;
import android.database.Cursor;
import android.os.Environment;
import android.util.Log;

import com.example.todo.data.ToDo;
import com.example.todo.datahandler.db.DbContract;

import java.io.File;
import java.util.ArrayList;

/**
 * manages ToDo datas.
 */
public class DataManager {
    private static String TAG = "DataManager";
    private static DataManager ourInstance = new DataManager();
    private static ArrayList<ToDo> todoList = new ArrayList<ToDo>();
    private static Context context = null;

    public static DataManager getInstance() {
        return ourInstance;
    }

    private DataManager() {
    }

    public void setContext(Context pContext){
        context = pContext;
    }

    /**
     * Add new ToDo item
     * @param pTodo
     * @return the row ID of the newly inserted ToDo
     */
    public long addTodo(ToDo pTodo){
        DbHandler db = new DbHandler(context);
        db.open();
        long id = db.insertToDo(pTodo);
        db.close();
        return id;
    }

    /**
     * update ToDo item
     * @param pTodo
     */
    public void updateTodo(ToDo pTodo){
        DbHandler db = new DbHandler(context);
        db.open();
        db.updateToDo(pTodo);
        db.close();
    }

    /**
     * Get todo
     * @param id
     * @return
     */
    public ToDo getTodo(long id){
        DbHandler db = new DbHandler(context);
        db.open();
        ToDo td = db.getToDo(id);
        db.close();
        return td;
    }

    /**
     * Delete a ToDo item
     * @param toDoID
     */
    public void deleteTodo(long toDoID){
        DbHandler db = new DbHandler(context);
        db.open();
        db.deleteToDo(toDoID);
        db.close();
        // delete the files
        deleteRecursive(new File(getToDoDir(toDoID)));
    }

    public ArrayList<ToDo> getToDoList(){
        Log.d(TAG, "getToDoList");

        DbHandler db = new DbHandler(context);
        db.open();
        todoList = db.getAllToDoArrayList(db.getAllToDo());
        db.close();

        return todoList;
    }

    /**
     * Returns a directory for the specified todo
     * @param toDoId
     * @return
     */
    public static String getToDoDir(long toDoId) {
        String dirName = Environment.getExternalStorageDirectory().getAbsolutePath() +
                "/ToDo/"+toDoId+"/";
        //Check the directory, create if not exist.
        File f = new File(dirName);
        if (!f.exists()){
            f.mkdirs();
        }
        return dirName;
    }

    /**
     * Delete a directory recursive
     * @param fileOrDirectory
     */
    public static void deleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory()) {
            for (File child : fileOrDirectory.listFiles()) {
                deleteRecursive(child);
            }
        }
        fileOrDirectory.delete();
    }

}
