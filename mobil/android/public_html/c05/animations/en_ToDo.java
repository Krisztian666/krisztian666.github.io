package com.example.todo.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Store data
 */
public class ToDo {
...
    private long date;
...

    /**
     * Init ToDo
     * @param name
     * @param priority
     * @param date
     * @param allDay
     * @param description
     */
    public ToDo(String name, Priority priority, long date, boolean allDay, String description) {
        this.name = name;
        this.priority = priority;
        this.date = date;
        this.allDay = allDay;
        this.description = description;
    }

    /**
     * Init ToDo
     * @param name
     * @param priority
     * @param date
     * @param allDay
     * @param description
     * @param url
     */
    public ToDo(String name, Priority priority, long date, boolean allDay, String description, String url) {
        this.name = name;
        this.priority = priority;
        this.date = date;
        this.allDay = allDay;
        this.description = description;
        this.url = url;
    }

...

    public long getDate() {
        return date;
    }

    public String getFormattedDate() {
        Calendar calEvent = Calendar.getInstance();
        calEvent.setTimeInMillis(date);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String formattedDate = dateFormat.format(calEvent.getTime());
        return formattedDate;
    }

    public void setDate(long date) {
        this.date = date;
    }

...
}
