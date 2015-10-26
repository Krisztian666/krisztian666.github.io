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

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

}
