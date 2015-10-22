package com.example.todo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.todo.data.ToDo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Handle ToDo elements
 */
public class ToDoAdapter extends BaseAdapter {

    private final List<ToDo> toDoList;
    private Context context;

    public ToDoAdapter(final Context pContext, ArrayList<ToDo> pToDoList) {
        this.toDoList = pToDoList;
        this.context = pContext;
    }

...

    /**
     * Setting displayed line items
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ToDo todo = toDoList.get(position);
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.todorow, null);

        TextView textViewName = (TextView) itemView.findViewById(R.id.nameTextView);
        textViewName.setText(todo.getName());

        TextView textViewDate = (TextView) itemView.findViewById(R.id.dateTextView);
        textViewDate.setText(todo.getDate());

        TextView urlView = (TextView)itemView.findViewById(R.id.urlTextView);
        urlView.setText(todo.getUrl());
        urlView.setOnClickListener(new View.OnClickListener() {//{speak:"Set OnClickListener"}
            @Override
            public void onClick(View v) {
                Log.i("ToDoAdapter", "URL clicked");//{speak:"Open URL if clicked."}
                openWebPage(todo.getUrl());
            }
        });


        return itemView;
    }

    private void openWebPage(String url) {
        Uri webPage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webPage);//{speak:"Create Intent"}
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);//{img:"scr-openurl.png", speak:"Launch the browser"}
        }
    }
}
