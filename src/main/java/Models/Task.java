package src.main.java.models;

import java.awt.Window;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import src.main.java.windows.WindowBase;

public class Task {
    //index incrementer
    static int idxIncr = 0;
    //Main collection for project, this data is shown on windows
    private static List<Task> data = new ArrayList<>();
    //Start and end time of task
    private int id;
    private LocalDateTime start;
    //End equals start by default
    private LocalDateTime end;
    private String title;
    private String description;
    //Importance of task
    private boolean priority;

    public Task(){}
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

    public static List<Task> getTasks(){
        return Task.data;
    }
    //Setters and data constraints
    public void setStart(LocalDateTime ns){
        
        if (ns.isBefore(LocalDateTime.now())) {
            throw new InputMismatchException("Start date cannot be before current date (" + LocalDateTime.now().format(DateTimeFormatter.ofPattern(WindowBase.getDateFormat())) + ")");
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

    public static LocalDateTime stringToLocalDate(String date) throws InputMismatchException{
        try {
            int[] dateData = new int[5];
            for (int i = 0; i < 5; i++) {
                dateData[i] = Integer.parseInt(date.split("[.\\:\\s]")[i]);
            }
            return LocalDateTime.of(dateData[0],dateData[1],dateData[2],dateData[3],dateData[4]);
        } catch (Exception e) {
            throw new InputMismatchException("Given date is in the wrong format (" + WindowBase.getDateFormat() + ") ");
        }
    }
    
    
    public static void addTask(Task t){
        Task.data.add(t);
    }
    public static void setTask(Task t){
        for (int i = 0; i < Task.data.size(); i++) {
            if (Task.data.get(i).getId() == t.getId()) {
                Task.data.set(i, t);
            }            
        }
    }
}
