package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.color.*;



public class Model {
    //constants and variables used across the project
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
    public static Color Primary = new Color(0, 122, 204);
    public static Color BackGround = new Color(30,30,30);  
}
