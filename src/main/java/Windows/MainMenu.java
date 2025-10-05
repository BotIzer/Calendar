package src.main.java.windows;
import javax.swing.*;

import java.awt.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;

import src.main.java.Models.*;

public class MainMenu extends WindowBase {
    //TEST DATA remove later
    public static ArrayList<Task> data = new ArrayList<>();
    
    public MainMenu() {
        
        //remove later
        for (int i = 0; i < 3; i++) {
            data.add(new Task("Test" + i, LocalDateTime.now()));
        }
        data.add(new Task("Test2", LocalDateTime.now().plusHours(4)));

        //Configuration of behaviour
        this.setSize(_resolution);
        this.setTitle("Calendar");
        this.setLayout(new GridLayout(3, 1, 20, 20));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Components
        JLabel title = new JLabel("Calendar");
        JPanel calendar = new JPanel();
        calendar.setLayout(new GridLayout(1,7));
        JButton newTaskBtn = new JButton("Add Task");
        //Fill calendar with tasks
        for (int i = 1; i < 8; i++) {
            DayContainer dc = new DayContainer(DayOfWeek.of(i).getDisplayName(TextStyle.FULL, getLocale()), data);
            calendar.add(dc);
        }
        //Styling
        calendar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.TOP);
        //Add components to frame
        this.add(title);
        this.add(calendar);
        this.add(newTaskBtn);
        this.setVisible(true);
    }
        
}
