package src.main.java.windows;
import javax.swing.*;

import src.main.java.models.*;

import java.awt.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;

public class WeekView extends WindowBase {
    private static JPanel calendar;
    private static JLabel today;
    public WeekView() {
        
        //Test data, remove later
        for (int i = 0; i < 3; i++) {
            Task.addTask(new Task("Test" + i, LocalDateTime.now()));
        }
        Task.addTask(new Task("Test2", LocalDateTime.now().plusHours(4)));

        //Configuration of behaviour
        this.setSize(resolution);
        this.setTitle("Calendar");
        this.setLayout(new GridLayout(3, 1, 20, 20));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Components
        calendar = new JPanel();
        today = new JLabel(LocalDateTime.now().format(DateTimeFormatter.ofPattern(WindowBase.dateOutFormat)));
        calendar.setLayout(new GridLayout(1,7));
        JButton newTaskBtn = new JButton("Add Task");
        //Fill calendar with tasks
        int date = WindowBase.today.getDayOfYear(); 
        for (int i = date - 3; i < date + 4; i++) {
            ArrayList<Task> dayList = new ArrayList<>();
            for (Task task : Task.getTasks()) {
                if (task.getStart().getDayOfYear() == i) {
                    dayList.add(task);
                }
            }
            String day = WindowBase.today.plusDays(i-date).getDayOfWeek().getDisplayName(TextStyle.FULL, getLocale());
            String dateString = WindowBase.today.plusDays(i-date).format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
            DayContainer dc = new DayContainer(day, dateString, dayList);
            calendar.add(dc);
        }
        //Styling
        calendar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
        today.setHorizontalAlignment(JLabel.CENTER);
        today.setVerticalAlignment(JLabel.CENTER);
        today.setFont(new Font("Times New Roman", Font.BOLD, 100));
        //Add components to frame
        this.add(today);
        this.add(calendar);
        this.add(newTaskBtn);
        this.setVisible(true);
    }
    //redraw calendar
    @Override
    public void refresh(List<Task> tl) {
        calendar.removeAll();
        int date = WindowBase.today.getDayOfYear(); 
        for (int i = date - 3; i < date + 4; i++) {
            ArrayList<Task> dayList = new ArrayList<>();
            for (Task task : Task.getTasks()) {
                if (task.getStart().getDayOfYear() == i) {
                    dayList.add(task);
                }
            }
            String day = WindowBase.today.plusDays(i-date).getDayOfWeek().getDisplayName(TextStyle.FULL, getLocale());
            String dateString = WindowBase.today.plusDays(i-date).format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
            DayContainer dc = new DayContainer(day, dateString, dayList);
            calendar.add(dc);
        }
        calendar.repaint();
        calendar.revalidate();
        this.repaint();
        this.revalidate();
    }
}
