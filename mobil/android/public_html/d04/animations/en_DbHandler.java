package com.example.todo.datahandler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.todo.datahandler.db.DbHelper;

/**
 * Handles database operations
 */
public class DbHandler {
    private Context context;
    private DbHelper dbHelper;
    private SQLiteDatabase db;

    public DbHandler(Context context) {
        this.context = context;
    }

    public void open() {
        dbHelper = new DbHelper(context);
    }
}
