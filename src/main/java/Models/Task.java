package src.main.java.Models;

import java.time.LocalDateTime;

public class Task {
    static int idxIncr = 0;
    //Start and end time of task
    private int id;
    private LocalDateTime start;
    //End equals start by default
    private LocalDateTime end;
    private String title;
    private String description;
    //Importance of task
    private boolean priority;

    public Task(){};
    public Task(LocalDateTime s, LocalDateTime e, String t, String d){
        start = s;
        end = e;
        title = t;
        description = d;
        id = idxIncr;
        idxIncr++;
    }
    //ONLY FOR TESTING REMOVE LATER
    public Task(String t, LocalDateTime date){
        title = t;
        start = date;
        end = date;
        description = null;
        id = idxIncr;
        idxIncr++;
    }
    //Getters
    public int getId() {return id;}
    public LocalDateTime getStart(){return start;}
    public LocalDateTime getEnd(){return end;}
    public String getTitle(){return title;}
    public String getDescription(){return description;}
    public boolean getPriority(){return priority;}
}
