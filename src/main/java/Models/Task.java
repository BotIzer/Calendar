package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.InputMismatchException;



public class Task {
    //index incrementer
    static int idxIncr = 0;
    //Main collection for project, this data is shown on windows
    //Start and end time of task
    private int id;
    private LocalDateTime start;
    private Integer[] duration;
    private String title;
    private String description;
    //Importance of task
    private boolean priority;

    public Task(){}
    public Task(LocalDateTime s, Integer[] dur, String t, String d){
        start = s;
        duration = dur;
        title = t;
        description = d;
        id = idxIncr;
        idxIncr++;
    }
    //ONLY FOR TESTING REMOVE LATER
    public Task(String t, LocalDateTime date){
        title = t;
        start = date;
        duration = new Integer[]{1,5};
        description = null;
        id = idxIncr;
        idxIncr++;
    }
    //Getters
    public int getId() {return id;}
    public LocalDateTime getStart(){return start;}
    public Integer[] getDuration(){return duration;}
    public String getTitle(){return title;}
    public String getDescription(){return description;}
    public boolean getPriority(){return priority;}


    //Setters and data constraints
    public void setTitle(String tit){
        if(tit.trim().isEmpty()) throw new IllegalArgumentException("Title cannot be empty");
        title = tit.trim();
    }
    public void setStart(LocalDateTime ns, LocalDateTime tmpNow){
        if(ns.equals(start)) return;
        if (ns.truncatedTo(ChronoUnit.MINUTES).isBefore(tmpNow.truncatedTo(ChronoUnit.MINUTES))) {
            throw new InputMismatchException("Start date cannot be before current date (" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")) + ")");
        }
        start = ns;
    }
    public void setDuration(Integer[] dur){
        if (dur.length != 2) throw new IllegalArgumentException("Illegal duration");
        if ((dur[0] < 0 || dur[0] > 24) && (dur[1] < 0 || dur[1] > 60)) throw new IllegalArgumentException("Illegal minute or hour");
        duration = dur;
    }
    public void setDesc(String nd){
        description = nd;
    }
    public void setPrio(boolean np){
        priority = np;
    }
    public static void setTask(Task t){
        for (int i = 0; i < Model.tasks.size(); i++) {
            if (Model.tasks.get(i).getId() == t.getId()) {
                Model.tasks.set(i, t);
                break;
            }            
        }
    }

    public static LocalDateTime stringToLocalDate(String date) throws InputMismatchException{
        try {
            int[] dateData = new int[5];
            for (int i = 0; i < 5; i++) {
                dateData[i] = Integer.parseInt(date.split("[.\\:\\s]")[i]);
            }
            return LocalDateTime.of(dateData[0],dateData[1],dateData[2],dateData[3],dateData[4]);
        } catch (Exception e) {
            throw new InputMismatchException("Given date is in the wrong format (" + "yyyy.MM.dd HH:mm" + ") ");
        }
    }
}
