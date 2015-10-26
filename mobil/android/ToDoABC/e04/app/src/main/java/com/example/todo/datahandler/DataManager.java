package com.example.todo.datahandler;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.todo.data.ToDo;
import com.example.todo.datahandler.db.DbContract;

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
     */
    public void addTodo(ToDo pTodo){
        DbHandler db = new DbHandler(context);
        db.open();
        db.insertToDo(pTodo);
        db.close();
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
    }

    public ArrayList<ToDo> getToDoList(){
        Log.d(TAG, "getToDoList");

        DbHandler db = new DbHandler(context);
        db.open();
        todoList = db.getAllToDoArrayList(db.getAllToDo());
        db.close();

        return todoList;
    }
}
