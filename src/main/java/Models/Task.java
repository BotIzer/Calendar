package src.main.java.Models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;

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
    //Setters and data constraints
    public void setStart(LocalDateTime ns){
        if (ns.isBefore(LocalDateTime.now())) {
            throw new InputMismatchException("Start date cannot be before current date (" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")) + ")");
        }
        start = ns;
    }
    public void setEnd(LocalDateTime ne){
        if (ne.isBefore(LocalDateTime.now())) {
            if (start != null && ne.isBefore(start)) {
                throw new InputMismatchException("End date cannot be before start date (" + start.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")) + ")");
            }
            throw new InputMismatchException("End date cannot be before current date (" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")) + ")");
        }
        end = ne;
    }
    public void setTitle(String nt){
        if (nt.isEmpty() || nt.trim().equals("")) {
            throw new InputMismatchException("Title cannot be empty");
        }
        title = nt;
    }
    //Description can be empty
    public void setDesc(String nd){
        description = nd;
    }
    public void setPrio(boolean np){
        priority = np;
    }

    public static LocalDateTime stringToLocalDate(String date){
        int[] dateData = new int[5];
        for (int i = 0; i < 5; i++) {
            dateData[i] = Integer.parseInt(date.split("[.\\:\\s]")[i]);
        }
        return LocalDateTime.of(dateData[0],dateData[1],dateData[2],dateData[3],dateData[4]);
    }
}
