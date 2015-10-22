package com.example.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.todo.data.ToDo;

import java.util.ArrayList;
import java.util.List;

/**
 * Handle ToDo elements
 */
public class ToDoAdapter extends BaseAdapter {

    private final List<ToDo> toDoList;

    public ToDoAdapter(final Context pContext, ArrayList<ToDo> pToDoList) {
        this.toDoList = pToDoList;
    }

    @Override
    public int getCount() {
        return toDoList.size();
    }

    public void addItem(ToDo pToDo) {
        toDoList.add(pToDo);
    }

    @Override
    public Object getItem(int position) {
        return toDoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Setting displayed line items
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ToDo todo = toDoList.get(position);
        LayoutInflater inflater = (LayoutInflater) parent.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.todorow, null);

        TextView textViewName = (TextView) itemView.findViewById(R.id.nameTextView);
        textViewName.setText(todo.getName());

        TextView textViewDate = (TextView) itemView.findViewById(R.id.dateTextView);
        textViewDate.setText(todo.getDate());
        return itemView;
    }
}
