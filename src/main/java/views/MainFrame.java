package views;
import javax.swing.*;

import components.CalendarTable;
import components.CustomClock;
import models.Model;
import models.Task;

import java.awt.*;
import java.time.LocalDateTime;

public class MainFrame extends WindowBase {
    private static CalendarTable calendar;
    private static CustomClock clock;
    private static View view = View.MONTH;
    public MainFrame() {
        
        //Test data, remove later
        for (int i = 0; i < 3; i++) {
            Model.tasks.add(new Task("Test" + i, LocalDateTime.now()));
        }
        Model.tasks.add(new Task("Test2", LocalDateTime.now().plusHours(4)));

        //Configuration of behaviour
        this.setSize(WindowBase.resolution);
        this.setTitle("Calendar");
        this.setLayout(new GridLayout(2, 1));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Components
        calendar = new CalendarTable(view);
        clock = new CustomClock();




        //Add components to frame
        this.add(clock);
        this.add(calendar);
        this.setVisible(true);
    }
    //refresh view calendar
    @Override
    public void refresh() {
        calendar.refresh(view);
        this.repaint();
        this.revalidate();
    }
}
