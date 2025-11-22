package views;
import javax.swing.*;

import components.CalendarTable;
import components.CustomClock;
import models.Model;
import models.Task;

import java.awt.*;
import java.time.LocalDateTime;

public class MainFrame extends WindowBase {
    private CalendarTable calendar;
    private CustomClock clock;
    private View view = View.MONTH;
    public MainFrame() {
        
        //Test data, remove later
        for (int i = 0; i < 3; i++) {
            Model.tasks.add(new Task("Test" + i, LocalDateTime.now()));
        }
        Model.tasks.add(new Task("Test2", LocalDateTime.now().plusHours(4)));

        //Configuration of behaviour
        this.setSize(WindowBase.resolution);
        this.setTitle("Calendar");
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //Components
        JMenuBar menuBar = new JMenuBar();
        JMenu views = new JMenu("View");
        JMenuItem week = new JMenuItem("Week");
        JMenuItem month = new JMenuItem("Month");
        //TODO year
        JMenuItem year = new JMenuItem("Year");
        week.addActionListener(e -> switchView(View.WEEK));
        month.addActionListener(e -> switchView(View.MONTH));
        year.addActionListener(e -> switchView(View.YEAR));
        
        views.add(week);
        views.add(month);
        views.add(year);
        menuBar.add(views);
        calendar = new CalendarTable(view);
        clock = new CustomClock();




        //Add components to frame
        this.setJMenuBar(menuBar);
        gc.gridy = 0;
        gc.weightx = 1;
        gc.fill = GridBagConstraints.BOTH;
        this.add(clock,gc);
        gc.weighty = 1;
        gc.gridy = 1;
        this.add(calendar,gc);
        this.setVisible(true);
    }
    //refresh view calendar
    @Override
    public void refresh() {
        calendar.refresh(view);
        this.repaint();
        this.revalidate();
    }
    private void switchView(View v){
        this.view = v;
        calendar.refresh(v);
        this.repaint();
        this.revalidate();
    }
}
