package views;
import javax.swing.*;

import models.Model;
import models.Task;
import src.components.CalendarTable;
import src.components.CustomClock;

import java.awt.*;
import java.time.LocalDateTime;

public class MainFrame extends WindowBase {
    private static CalendarTable calendar;
    private static CustomClock clock;
    public MainFrame() {
        
        //Test data, remove later
        for (int i = 0; i < 3; i++) {
            Model.tasks.add(new Task("Test" + i, LocalDateTime.now()));
        }
        Model.tasks.add(new Task("Test2", LocalDateTime.now().plusHours(4)));

        //Configuration of behaviour
        this.setSize(WindowBase.resolution);
        this.setTitle("Calendar");
        this.setLayout(new GridLayout(3, 1, 20, 20));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Components
        calendar = new CalendarTable(View.WEEK);
        clock = new CustomClock();
        calendar.setLayout(new GridLayout(1,7));
        JButton newTaskBtn = new JButton("Add Task");
        newTaskBtn.addActionListener(e -> {calendar.removeAll(); this.refresh();});




        //Add components to frame
        this.add(clock);
        this.add(calendar);
        this.add(newTaskBtn);
        this.setVisible(true);
    }
    //refresh view calendar
    @Override
    public void refresh() {
        calendar.refresh();
        this.repaint();
        this.revalidate();
    }
}
