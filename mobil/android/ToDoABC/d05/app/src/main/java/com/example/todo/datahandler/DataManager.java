package com.example.todo.datahandler;

import android.content.Context;

import com.example.todo.data.ToDo;

import java.util.ArrayList;

/**
 * manages ToDo datas.
 */
public class DataManager {
    private static DataManager ourInstance = new DataManager();
    private static ArrayList<ToDo> todoList ;
    private static Context context = null;

    public static DataManager getInstance() {
        return ourInstance;
    }

    private DataManager() {
        if (todoList == null) {
            todoList = FileHandler.readToDoList(context);
        }

    }

    public void setContext(Context pContext){
        context = pContext;
    }

    /**
     * Add new ToDo item
     * @param pTodo
     */
    public void addTodo(ToDo pTodo){
        todoList.add(pTodo);
        FileHandler.writeToDoList(context, todoList);
    }

    /**
     * Delete a ToDo item
     * @param toDoID
     */
    public void deleteTodo(int toDoID){
        todoList.remove(toDoID);
        FileHandler.writeToDoList(context, todoList);
    }

    public ArrayList<ToDo> getToDoList(){
        todoList = FileHandler.readToDoList(context); // Read it once again for demonstration
        return todoList;
    }
}
