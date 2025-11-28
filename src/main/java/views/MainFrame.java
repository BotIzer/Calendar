package main.java.views;
import javax.swing.*;

import main.java.components.CalendarTable;
import main.java.components.CustomClock;
import main.java.components.Details;
import main.java.models.FileHandler;
import main.java.models.Model;
import main.java.models.Task;

import java.awt.*;

public class MainFrame extends WindowBase {
    private CalendarTable calendar;
    private CustomClock clock;
    private View view = View.WEEK;
    public MainFrame() {
        
        FileHandler.readFromJson();

        //Configuration of behaviour
        this.setSize(WindowBase.resolution);
        this.setTitle("Calendar");
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setBackground(Model.BackGroundColor);
        //Components
        JMenuBar menuBar = new JMenuBar();
        JMenu views = new JMenu("View");
        JMenuItem week = new JMenuItem("Week");
        JMenuItem month = new JMenuItem("Month");
        JMenuItem year = new JMenuItem("Year");
        week.addActionListener(e -> switchView(View.WEEK));
        month.addActionListener(e -> switchView(View.MONTH));
        year.addActionListener(e -> switchView(View.YEAR));
        JButton addTask = new JButton("Add Task"); 
        menuBar.setBackground(Model.BackGroundColor);
        views.setBackground(Model.BackGroundColor);
        views.setForeground(Model.TextColor);
        week.setBackground(Model.BackGroundColor);
        week.setForeground(Model.TextColor);
        month.setBackground(Model.BackGroundColor);
        month.setForeground(Model.TextColor);
        year.setBackground(Model.BackGroundColor);
        year.setForeground(Model.TextColor);
        addTask.setBackground(Model.PrimaryColor);
        addTask.setForeground(Model.TextColor);

        
        addTask.setFocusable(false);
        addTask.addActionListener(e -> new Details(new Task()));
        
        views.add(week);
        views.add(month);
        views.add(year);
        menuBar.add(views);
        menuBar.add(addTask);
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
    @Override
    public void dispose() {
        super.dispose();
        FileHandler.writeToJson(Model.tasks);
        System.exit(0);
    }

}
