package com.example.todo.datahandler.db;

/**
 * Constants
 */
public class DbContract {
    // this file sores the database
    public static final String DB_NAME = "tododata.db";
    //db version number
    public static final int DB_VERSION = 2;
    // create the tables
    public static final String DB_CREATE_ALL = ToDo.DB_CREATE;
    // drop the tables
    public static final String DB_DROP_ALL = ToDo.DB_DROP;

    /**
     * Stores ToDo constants
     */
    public static class ToDo {
        // table name
        public static final String TABLE_NAME = "todo";
        // column names
        public static final String CN_ROWID = "_id";
        public static final String CN_NAME = "name";
        public static final String CN_PRIORITY = "priority";
        public static final String CN_DATE = "date";
        public static final String CN_ALLDAY = "allday";
        public static final String CN_DESCRIPTION = "descr";
        public static final String CN_URL = "url";
        public static final String CN_CONTACT_NAME = "contactname";
        public static final String CN_CONTACT_PHONE = "contactphone";
        public static final String CN_GPS_LONG = "gpslong";
        public static final String CN_GPS_LAT = "gpslat";

        public static final String DB_CREATE =
                "create table if not exists " + TABLE_NAME +
                " ( " +
                CN_ROWID + " integer primary key autoincrement, " +
                CN_NAME + " text not null, " +
                CN_PRIORITY + " text, " +
                CN_DATE + " integer, " +
                CN_ALLDAY + " integer, " +
                CN_DESCRIPTION + " text, " +
                CN_URL + " text, " +
                CN_CONTACT_NAME + " text, " +
                CN_CONTACT_PHONE + " text, " +
                CN_GPS_LONG + " text, " +
                CN_GPS_LAT + " text " +
                        "); ";

        public static final String DB_DROP =
                "drop table if exists " + TABLE_NAME + "; ";

    }
}
