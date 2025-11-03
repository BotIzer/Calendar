package src.main.java.views;
import javax.swing.*;

import src.components.CalendarTable;
import src.components.CustomClock;
import src.main.java.models.*;

import java.awt.*;
import java.time.LocalDateTime;

public class WeekView extends WindowBase {
    private static CalendarTable calendar;
    private static CustomClock clock;
    public WeekView() {
        
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
        newTaskBtn.addActionListener(e -> clock.refresh());




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
