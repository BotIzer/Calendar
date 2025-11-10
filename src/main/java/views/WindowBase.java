package views;
import javax.swing.*;

import java.awt.*;

//Base class for frames that all custom frames inherit
public class WindowBase extends JFrame {
    //Constant for screen size
    static Dimension resolution = new Dimension(1024, 768);
    //Singleton, this is the active frame
    private static WindowBase active;
    //enum for views
    public enum View{
        WEEK,
        MONTH,
        YEAR
    }
    
    //Active frame getter, returns main menu if active is null
    public static WindowBase getInstance(){
       if (active == null) {
           active = new MainFrame();
       }
       return active;
    }
    //Function to switch frames
    public static void switchWindow(WindowBase nw){
        active = nw;
    }
    //"Virtual" function to repaint contents of windows
    public void refresh(){}
}