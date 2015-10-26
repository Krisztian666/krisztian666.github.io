package com.example.todo.datahandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.todo.data.ToDo;
import com.example.todo.datahandler.db.DbContract;
import com.example.todo.datahandler.db.DbHelper;

/**
 * Handles database operations
 */
public class DbHandler {
    private static String TAG = "DbHandler";

    private Context context;
    private DbHelper dbHelper;
    private SQLiteDatabase db;

    public DbHandler(Context context) {
        this.context = context;
    }

    public void open() {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    /**
     * Insert ToDo
     * @param todo
     * @return the row id
     */
    public long insertToDo(ToDo todo) {
        Log.d(TAG, "Insert ToDo");

        int iAllDay = 0;//false
        if (todo.isAllDay()) {
            iAllDay = 1;
        }
        ContentValues values = new ContentValues();
        values.put(DbContract.ToDo.CN_NAME, todo.getName());
        values.put(DbContract.ToDo.CN_PRIORITY, todo.getPriority().name());
        values.put(DbContract.ToDo.CN_DATE, todo.getDate());
        values.put(DbContract.ToDo.CN_ALLDAY, iAllDay);
        values.put(DbContract.ToDo.CN_DESCRIPTION, todo.getDescription());
        values.put(DbContract.ToDo.CN_URL, todo.getUrl());
        values.put(DbContract.ToDo.CN_CONTACT_NAME, todo.getContact().getContactName());
        values.put(DbContract.ToDo.CN_CONTACT_PHONE, todo.getContact().getPhoneNumber());

        return db.insert(DbContract.ToDo.TABLE_NAME, null, values);
    }

    /**
     * Delete ToDo
     * @param rowId
     */
    public void deleteToDo(long rowId){
        Log.d(TAG, "delete ToDo:" + rowId);

        String whereClause = DbContract.ToDo.CN_ROWID + " LIKE ?";
        String[] whereArgs = new String[]{String.valueOf(rowId)};
        db.delete(DbContract.ToDo.TABLE_NAME, whereClause, whereArgs);
    }



}
