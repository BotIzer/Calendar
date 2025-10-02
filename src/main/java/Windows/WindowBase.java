package src.main.java.Windows;
import javax.swing.*;
import java.awt.*;

public class WindowBase extends JFrame {
    static Dimension _resolution = new Dimension(640, 480);
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