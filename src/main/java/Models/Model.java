package models;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import java.awt.Color;



public class Model {
    private Model(){}
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
    public static int tasksOfMonth(int m){
        int n = 0;
        for (Task task : tasks) {
            if (task.getStart().getMonth() == Month.of(m)) {
                n++;
            }
        }
        return n;
    }

    public static Border basicBorder = BorderFactory.createLineBorder(Color.BLACK);
    public static Color PrimaryColor = new Color(0, 122, 204);
    public static Color SecondaryColor = new Color(61,61,61);
    public static Color BackGroundColor = new Color(30,30,30);
    public static Color TextColor = Color.WHITE;
}
