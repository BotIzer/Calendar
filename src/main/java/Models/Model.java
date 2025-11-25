package models;

import java.time.LocalDateTime;
import java.util.ArrayList;




public class Model {
    protected static String dateOutFormat = "yyyy.MM.dd";
    protected static String timeOutFormat = "HH:mm:ss";
    public static LocalDateTime now = LocalDateTime.now();
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static String getDateFormat(){
        return Model.dateOutFormat;
    }
    public static String getTimeFormat(){
        return Model.timeOutFormat;
    }
    
}
