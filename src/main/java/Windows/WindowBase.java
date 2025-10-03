package src.main.java.windows;
import javax.swing.*;
import java.awt.*;

public class WindowBase extends JFrame {
    static Dimension _resolution = new Dimension(1024, 768);
    private static WindowBase active;
    
    public static WindowBase getInstance(){
       if (active == null) {
           active = new MainMenu();
       }
       return active;
    }

    public static void switchWindow(WindowBase nw){
        active = nw;
    }
}