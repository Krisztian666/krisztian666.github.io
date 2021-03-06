package com.example.todo.datahandler.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Database helper class
 */
public class DbHelper extends SQLiteOpenHelper{

/**
 * Create a helper object to create, open, and/or manage a database.
 * This method always returns very quickly.  The database is not actually
 * created or opened until one of {@link #getWritableDatabase} or
 * {@link #getReadableDatabase} is called.
 *
 * @param context to use to open or create the database
 */
public DbHelper(Context context ) {
    super(context, DbContract.DB_NAME, null, DbContract.DB_VERSION);
}

/**
 * Called when the database is created for the first time. This is where the
 * creation of tables and the initial population of the tables should happen.
 *
 * @param db The database.
 */
@Override
public void onCreate(SQLiteDatabase db) {
    db.execSQL(DbContract.DB_CREATE_ALL);

}

/**
 * Called when the database needs to be upgraded. The implementation
 * should use this method to drop tables, add tables, or do anything else it
 * needs to upgrade to the new schema version.
 * 
 * The SQLite ALTER TABLE documentation can be found here:
 * "http:/ /sqlite.org/lang_altertable.html". If you add new columns
 * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
 * you can use ALTER TABLE to rename the old table, then create the new table and then
 * populate the new table with the contents of the old table.
 *
 * This method executes within a transaction.  If an exception is thrown, all changes
 * will automatically be rolled back.
 *
 * @param db         The database.
 * @param oldVersion The old database version.
 * @param newVersion The new database version.
 */
@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    / /we drop the data, and create the new db 
    / /        for simplicity. ALL TODO WILL BE LOST!
    db.execSQL(DbContract.DB_DROP_ALL);
    onCreate(db);
}

}
