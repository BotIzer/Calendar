package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;



public class Model {
    protected static String dateOutFormat = "yyyy.MM.dd";
    protected static String timeOutFormat = "HH:mm:ss";
    public static LocalDateTime now = LocalDateTime.now();
    public static List<Task> tasks = new ArrayList<>();
    public static String getDateFormat(){
        return Model.dateOutFormat;
    }
    public static String getTimeFormat(){
        return Model.timeOutFormat;
    }
    
}
