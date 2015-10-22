package com.example.todo.data;

/**
 * Store data
 */
public class ToDo {

    public enum Priority {LOW, MED, HIGH }

    private String name;
    private Priority priority;
    private String date;
    private boolean allDay;
    private String description;
    private String url;


    public ToDo(String name, Priority priority, String date, boolean allDay, String description) {
        this.name = name;
        this.priority = priority;
        this.date = date;
        this.allDay = allDay;
        this.description = description;
    }

    public ToDo(String name, Priority priority, String date, boolean allDay, String description, String url) {
        this.name = name;
        this.priority = priority;
        this.date = date;
        this.allDay = allDay;
        this.description = description;
        this.url = url;
    }

...

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

}
