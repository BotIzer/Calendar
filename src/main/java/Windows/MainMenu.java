package src.main.java.Windows;
import javax.swing.*;
import java.awt.*;




public class MainMenu extends WindowBase {
    public MainMenu() {
        this.setSize(_resolution);
        this.setTitle("Calendar");
        Label title = new Label("Calendar");
        this.add(title);
        this.setLocation((int)_resolution.getWidth() / 2, (int)_resolution.getHeight() / 2);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
        
}
