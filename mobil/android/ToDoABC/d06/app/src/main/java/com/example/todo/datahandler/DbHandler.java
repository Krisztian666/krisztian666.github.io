package com.example.todo.datahandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.todo.data.Contact;
import com.example.todo.data.ToDo;
import com.example.todo.datahandler.db.DbContract;
import com.example.todo.datahandler.db.DbHelper;

import java.util.ArrayList;

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
     * Update ToDo
     * @param todo
     * @return the row id
     */
    public long updateToDo(ToDo todo) {
        Log.d(TAG, "Update ToDo: " + todo.getId());

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

        String whereClause = DbContract.ToDo.CN_ROWID + " LIKE ?";
        String[] whereArgs = new String[]{String.valueOf(todo.getId())};

        return db.update(DbContract.ToDo.TABLE_NAME, values, whereClause, whereArgs);
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


    /**
     * Get todo
     * @param id
     * @return
     */
    public ToDo getToDo(long id){
        String[] columns = new String[]{
                DbContract.ToDo.CN_ROWID,
                DbContract.ToDo.CN_NAME,
                DbContract.ToDo.CN_PRIORITY,
                DbContract.ToDo.CN_DATE,
                DbContract.ToDo.CN_ALLDAY,
                DbContract.ToDo.CN_DESCRIPTION,
                DbContract.ToDo.CN_URL,
                DbContract.ToDo.CN_CONTACT_NAME,
                DbContract.ToDo.CN_CONTACT_PHONE
        };

        String whereClause = DbContract.ToDo.CN_ROWID+"=?";
        String [] whereArgs = {"" + id};
        Cursor cursor = db.query(DbContract.ToDo.TABLE_NAME,
                columns,
                whereClause,
                whereArgs,
                null,
                null,
                null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                return toDoFromCursor(cursor);
            }
        }
        return null;
    }

    /**
     * Get all todo
     * @return
     */
    public Cursor getAllToDo(){
        String[] columns = new String[]{
                DbContract.ToDo.CN_ROWID,
                DbContract.ToDo.CN_NAME,
                DbContract.ToDo.CN_PRIORITY,
                DbContract.ToDo.CN_DATE,
                DbContract.ToDo.CN_ALLDAY,
                DbContract.ToDo.CN_DESCRIPTION,
                DbContract.ToDo.CN_URL,
                DbContract.ToDo.CN_CONTACT_NAME,
                DbContract.ToDo.CN_CONTACT_PHONE
        };
        return db.query(DbContract.ToDo.TABLE_NAME, columns, null, null, null, null, DbContract.ToDo.CN_DATE); // order by date
    }

    /**
     * Create ToDo object from cursor data
     * @param cursor
     * @return
     */
    private ToDo toDoFromCursor(Cursor cursor){
        int cAllday = cursor.getInt(cursor.getColumnIndex(DbContract.ToDo.CN_ALLDAY));
        boolean allday = false;
        if (cAllday == 1){
            allday = true;
        }
        String cPriority = cursor.getString(cursor.getColumnIndex(DbContract.ToDo.CN_PRIORITY));
        ToDo.Priority pr = ToDo.Priority.HIGH;
        if ("LOW".equals(cPriority)){
            pr = ToDo.Priority.LOW;
        } else if ("MED".equals(cPriority)){
            pr = ToDo.Priority.MED;
        }

        Contact contact =  new Contact(cursor.getString(cursor.getColumnIndex(DbContract.ToDo.CN_CONTACT_NAME)),
                cursor.getString(cursor.getColumnIndex(DbContract.ToDo.CN_CONTACT_PHONE)), "");

        ToDo td = new ToDo(cursor.getString(cursor.getColumnIndex(DbContract.ToDo.CN_NAME)),
                pr,
                cursor.getLong(cursor.getColumnIndex(DbContract.ToDo.CN_DATE)),
                allday,
                cursor.getString(cursor.getColumnIndex(DbContract.ToDo.CN_DESCRIPTION)),
                cursor.getString(cursor.getColumnIndex(DbContract.ToDo.CN_URL)),
                contact
        );
        td.setId(cursor.getLong(cursor.getColumnIndex(DbContract.ToDo.CN_ROWID)));

        return td;
    }

    /**
     * Get all todo
     * @param cursor
     * @return
     */
    public ArrayList<ToDo> getAllToDoArrayList(Cursor cursor){
        Log.d(TAG, "getAllToDoArrayList");
        ArrayList<ToDo> list = new ArrayList<ToDo>();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    list.add(toDoFromCursor(cursor));
                } while (cursor.moveToNext());
            }
        }

        return list;
    }

    /**
     * For testing
     * @param cursor
     */
    public void logGetAllToDoQuery(Cursor cursor){
        Log.i(TAG, "Query result:");
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Log.i(TAG, cursor.getString(cursor.getColumnIndex(DbContract.ToDo.CN_NAME)) + " " +
                            cursor.getString(cursor.getColumnIndex(DbContract.ToDo.CN_DATE)));
                } while (cursor.moveToNext());
            }
        }
    }

}
