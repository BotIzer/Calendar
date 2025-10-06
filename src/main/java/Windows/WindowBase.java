package src.main.java.windows;
import javax.swing.*;
import java.awt.*;
//Base class for frames that all custom frames inherit
public class WindowBase extends JFrame {
    //Constant for screen size
    static Dimension _resolution = new Dimension(1024, 768);
    static String dateOutFormat = "yyyy.MM.dd HH:dd";
    //Singleton, this is the active frame
    private static WindowBase active;
    //Active frame getter, returns main menu if active is null
    public static WindowBase getInstance(){
       if (active == null) {
           active = new MainMenu();
       }
       return active;
    }
    //Function to switch frames
    public static void switchWindow(WindowBase nw){
        active = nw;
    }
}