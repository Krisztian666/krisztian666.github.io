package com.example.todo.data; //{speak:"Create data package"}

/**
 * Store ToDo data
 */
public class ToDo {//{speak:"Create ToDo class"}

    public enum Priority {LOW, MED, HIGH } //{speak:"Define Priority types"}

    private String name; //{speak:"Declare ToDo name"}
    private Priority priority; //{speak:"Declare ToDo priority"}
    private String date; //{speak:"Declare ToDo date"}
    private boolean allDay; //{speak:"Declare ToDo allDay"}
    private String description; //{speak:"Declare ToDo description"}


    public ToDo(String name, Priority priority, 
            String date, boolean allDay, String description) { //{speak:"Create constructor"}
        this.name = name;
        this.priority = priority;
        this.date = date;
        this.allDay = allDay;
        this.description = description;
    }

    public String getName() { //{speak:"Create getter"}
        return name;
    }

    public void setName(String name) {//{speak:"and setter methods."}
        this.name = name;
    }

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
}
