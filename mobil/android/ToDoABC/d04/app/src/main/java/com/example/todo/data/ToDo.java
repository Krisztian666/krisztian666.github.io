package com.example.todo.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Store data
 */
public class ToDo {

    public enum Priority {LOW, MED, HIGH }

    private String name;
    private Priority priority;
    private long date;
    private boolean allDay;
    private String description;
    private String url = "";
    private Contact contact = new Contact();

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

    /**
     * Init ToDo
     * @param name
     * @param priority
     * @param date
     * @param allDay
     * @param description
     * @param url
     * @param contact
     */
    public ToDo(String name, Priority priority, long date, boolean allDay, String description, String url, Contact contact) {
        this.name = name;
        this.priority = priority;
        this.date = date;
        this.allDay = allDay;
        this.description = description;
        this.url = url;
        this.contact = contact;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

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

    public boolean isAllDay() {
        return allDay;
    }

    public void setAllDay(boolean allDay) {
        this.allDay = allDay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    public Contact getContact() { return contact; }

    public void setContact(Contact contact) { this.contact = contact; }
}
