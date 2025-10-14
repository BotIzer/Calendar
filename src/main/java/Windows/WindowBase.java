package src.main.java.windows;
import javax.swing.*;

import src.main.java.models.Task;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
//Base class for frames that all custom frames inherit
public class WindowBase extends JFrame {
    //Constant for screen size
    static Dimension resolution = new Dimension(1024, 768);
    protected static String dateOutFormat = "yyyy.MM.dd HH:dd";
    protected static LocalDateTime today = LocalDateTime.now();
    //Singleton, this is the active frame
    private static WindowBase active;
    //Active frame getter, returns main menu if active is null
    public static WindowBase getInstance(){
       if (active == null) {
           active = new WeekView();
       }
       return active;
    }
    //Function to switch frames
    public static void switchWindow(WindowBase nw){
        active = nw;
    }

    //Getters
    public static String getDateFormat(){
        return WindowBase.dateOutFormat;
    }
    //"Virtual" function to repaint contents of windows
    public void refresh(java.util.List<Task> tl){}
}