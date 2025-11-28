package models;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.InputMismatchException;



public class Task {
    static int idxIncr = 0;
    //Main collection for project
    private int id;
    private LocalDateTime start;
    private Integer[] duration;
    private String title;
    private String description;
    private boolean priority;

    public Task(){
        title = "";
        description = "";
        start = Model.now;
        duration = new Integer[]{0,0};
    }
    public Task(int i, LocalDateTime s, Integer[] dur, String t, String d, boolean p){
        id = i;
        start = s;
        duration = dur;
        title = t;
        description = d;
        priority = p;
    }
    public Task(LocalDateTime s, Integer[] dur, String t, String d){
        start = s;
        duration = dur;
        title = t;
        description = d;
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
        if (dur[0] == 0 && dur[1] == 0) throw new IllegalArgumentException("Illegal duration(0:0)");
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
    public static LocalDateTime makeDate(Date newStart, Integer[] duration){
        if(duration[0] == 0 && duration[1] == 0) throw new IllegalArgumentException("Duration has to be at least 1 minute");
        String tmp = LocalDateTime.ofInstant(newStart.toInstant(), ZoneId.systemDefault()).toString();
        tmp = tmp.split("T")[0];
        tmp += "T";
        tmp += duration[0] < 10 ? "0" + duration[0].toString() : duration[0].toString();
        tmp += ":";
        tmp += duration[1] < 10 ? "0" + duration[1].toString() : duration[1].toString();
        return LocalDateTime.parse(tmp);
        
    }
    public String serialize(){
        return "{\"id\": " + this.getId() + ","+
                      "\"title\": " + "\"" + this.getTitle() + "\"" + "," +
                      "\"description\": " + "\""+ this.getDescription() + "\"" + "," +
                      "\"start\": " + "\"" + this.getStart().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) +"\"" + "," +
                      "\"duration\": " + "[" + this.getDuration()[0] + ", " + this.getDuration()[1] + "]" + "," + 
                      "\"priority\": " + this.getPriority() +
                      "}";
    }
    public static Task deSerialize(String enrty){
        String tmp = enrty.replaceAll("[\"{]|},", "").replace(", ", "-");
        String[] map = tmp.split("[,:]");
        int id = Integer.parseInt(map[1].trim());
        String title = map[3].trim();
        String desc = map[5].trim();
        String st = map[7].trim() + ":" + map[8] +":"+ map[9].replace("Z", "");
        LocalDateTime start = LocalDateTime.parse(st, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String[] hm = map[11].replace("[", "").replace("]", "").split("-");
        Integer[] duration = {Integer.valueOf(hm[0].trim()), Integer.valueOf(hm[1].trim())};
        boolean prio = Boolean.parseBoolean(map[13].trim());
        return new Task(id, start, duration, title, desc, prio);
    }
}
