package src.main.java.Windows;
import javax.swing.*;

import java.awt.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;

import src.main.java.Models.*;

public class MainMenu extends WindowBase {
    //TEST DATA
    public static ArrayList<Task> data = new ArrayList<>();
    
    public MainMenu() {
        for (int i = 0; i < 3; i++) {
            data.add(new Task("Test" + i, LocalDateTime.now()));
        }
        data.add(new Task("Test2", LocalDateTime.now().plusHours(4)));
        this.setSize(_resolution);
        this.setTitle("Calendar");
        
        this.setLayout(new GridLayout(3, 1, 20, 20));

        JLabel title = new JLabel("Calendar");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.TOP);
        
         Container calendar = new Container();
        
        calendar.setLayout(new GridLayout(1,7));
        for (int i = 1; i < 8; i++) {
            DayContainer dc = new DayContainer(DayOfWeek.of(i).getDisplayName(TextStyle.FULL, getLocale()), data);
            // JLabel name = new JLabel(DayOfWeek.of(i).getDisplayName(TextStyle.FULL, getLocale()));
            // name.setVerticalAlignment(JLabel.TOP);
            // name.setHorizontalAlignment(JLabel.CENTER);
            // name.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            // if(i == LocalDate.now().getDayOfWeek().getValue()){
            //     name.setForeground(Color.BLUE);
            // }
            // calendar.add(name);
            calendar.add(dc);
        }
        this.add(title);
        this.add(calendar);
        
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
        
}
