package main.java.components;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import main.java.models.Model;
import main.java.models.Task;
import main.java.views.WindowBase.View;



public class CalendarTable extends JPanel{
    
    protected View view = View.WEEK;
    
    public CalendarTable(View v){
        this.setBackground(Model.BackGroundColor);
        if(v == View.WEEK) buildWeek();
        if (v == View.MONTH) buildMonth();
        if (v == View.YEAR) buildYear();
    }
    private void buildWeek(){
        this.setLayout(new GridLayout(1, 7));
        int date = Model.now.getDayOfYear(); 
        for (int i = date - 3; i < date + 4; i++) {
            ArrayList<Task> dayList = new ArrayList<>();
            for (Task task : Model.tasks) {
                if (task.getStart().getDayOfYear() == i) {
                    dayList.add(task);
                }
            }
            String day = Model.now.plusDays((long)i-date).getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            String dateString = Model.now.plusDays((long)i-date).format(DateTimeFormatter.ofPattern(Model.getDateFormat()));
            DayContainer dc = new DayContainer(day, dateString, dayList, View.WEEK);
            this.add(dc);
        }
    }
    private void buildMonth(){
        this.setLayout(new GridLayout(5, 7));
        for (int i = 0; i < 31; i++) {
            ArrayList<Task> dayList = new ArrayList<>();
            for (Task task : Model.tasks) {
                if (task.getStart().getDayOfYear() == Model.now.plusDays(i).getDayOfYear()) {
                    dayList.add(task);
                }
            }
            String day = Model.now.plusDays(i).getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            String dateString = String.valueOf(Model.now.plusDays(i).getDayOfMonth());
            DayContainer dc = new DayContainer(day, dateString, dayList, View.MONTH);
            this.add(dc);
        }
    }
    private void buildYear(){
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBackground(Model.BackGroundColor);
        for (int i = 1; i < 13; i++) {
            JPanel row = new JPanel();
            row.setBackground(Model.BackGroundColor);
            row.setLayout(new GridBagLayout());
            GridBagConstraints gc = new GridBagConstraints();
            JLabel monthLbl = new JLabel(Month.of(i).getDisplayName(TextStyle.FULL, Locale.ENGLISH) + ": ", SwingConstants.RIGHT);
            monthLbl.setFont(new Font("Times New Roman", Font.PLAIN ,22));
            monthLbl.setSize(monthLbl.getHeight(), 50);
            monthLbl.setForeground(Model.TextColor);
            JProgressBar progress = new JProgressBar();
            progress.setStringPainted(true);
            progress.setValue(Model.tasksOfMonth(i) / 31);
            progress.setBackground(Model.TextColor);
            progress.setForeground(Model.PrimaryColor);
            gc.fill = GridBagConstraints.HORIZONTAL;
            gc.gridx = 0;
            gc.weightx = 0;
            gc.insets = new Insets(5, 5, 5, 5);
            row.add(monthLbl, gc);
            gc.gridx = 1;
            gc.weightx = 0;
            row.add(progress, gc);
            container.add(row);
        }
        JScrollPane pane = new JScrollPane(container);
        pane.getViewport().setForeground(Model.TextColor);
        pane.getViewport().setBackground(Model.BackGroundColor);
        pane.setHorizontalScrollBar(null);
        this.add(pane);
    }
    public void refresh(View v){
        this.removeAll();
        switch (v) {
            case View.WEEK:
                buildWeek();
                break;
            case View.MONTH:
                buildMonth();
                break;
            case View.YEAR:
                buildYear();
                break;
            default:
                buildWeek();
                break;
        }
        this.repaint();
        this.revalidate();
    }
}
