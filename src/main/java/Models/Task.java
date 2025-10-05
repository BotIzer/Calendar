package src.main.java.Models;

import java.time.LocalDateTime;

public class Task {
    private LocalDateTime start;
    private LocalDateTime end;
    private String title;
    private String description;
    private boolean priority;

    public Task(){};
    public Task(LocalDateTime s, LocalDateTime e, String t, String d){
        start = s;
        end = e;
        title = t;
        description = d;
    };

    //ONLY FOR TESTING REMOVE LATER
    public Task(String t, LocalDateTime date){
        title = t;
        start = date;
        end = date;
        description = null;
    }

    public LocalDateTime getStart(){return start;}
    public LocalDateTime getEnd(){return end;}
    public String getTitle(){return title;}
    public String getDescription(){return description;}
    public boolean getPriority(){return priority;}
}
