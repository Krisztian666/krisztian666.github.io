package com.example.todo.data;

import java.util.ArrayList;

/**
 * manages ToDo datas.
 */
public class DataManager {//{speak:"Create DataManager"}
    private static DataManager ourInstance = new DataManager();
    private static ArrayList<ToDo> todoList ;

    public static DataManager getInstance() {
        return ourInstance;
    }

    private DataManager() {
        if (todoList == null) {
            todoList = new ArrayList<ToDo>();
            / /example list
            todoList.add(new ToDo("Todo 1", ToDo.Priority.HIGH, 
                    1446337020170L, true, "Description 1", 
                    "http:/ /developer.android.com/guide/components/intents-common.html"));
            todoList.add(new ToDo("Todo 2", ToDo.Priority.LOW, 
                    1446463200288L, true, "Description 2"));
            todoList.add(new ToDo("Todo 3", ToDo.Priority.MED, 
                    1446564600932L, true, "Description 3"));
        }

    }

    /**
     * Add new ToDo item
     * @param pTodo
     */
    public void addTodo(ToDo pTodo){
        todoList.add(pTodo);
    }

    /**
     * Delete a ToDo item
     * @param toDoID
     */
    public void deleteTodo(int toDoID){
        todoList.remove(toDoID);
    }

    public ArrayList<ToDo> getToDoList(){
        return todoList;
    }
}
