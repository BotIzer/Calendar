package main.java.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

import main.java.models.Model;
import main.java.models.Task;
import main.java.models.TaskTableModel;
import main.java.views.WindowBase.View;

//Container for day in table
public class DayContainer extends JPanel{
    private ArrayList<Task> tasks;
    public DayContainer(String dayString, String dateString, List<Task> dayTasks, View v){
        this.setBackground(Model.BackGroundColor);
        tasks = new ArrayList<>(dayTasks);
        if (v == View.WEEK) buildWeek(dayString, dateString);
        if (v == View.MONTH) buildMonth(dayString, dateString);
   }
    private void buildWeek(String dayString, String dateString){
        this.setLayout(new GridLayout(5, 1));
        ArrayList<Task> morning = new ArrayList<>();
        ArrayList<Task> noon = new ArrayList<>();
        ArrayList<Task> afterNoon = new ArrayList<>();
        ArrayList<Task> night = new ArrayList<>();
        for (Task task : tasks) {

            if (task.getStart().getHour() < 10) {
                morning.add(task);
            } else if (task.getStart().getHour() >= 10 && task.getStart().getHour() <= 12) {
                noon.add(task);
            } else if (task.getStart().getHour() >= 12 && task.getStart().getHour() <= 18) {
                afterNoon.add(task);
            } else if (task.getStart().getHour() > 18) {
                night.add(task);
            } 
        }        
        
        JLabel day = new JLabel(dayString);
        JLabel date = new JLabel(dateString);
        JPanel title = new JPanel();
        title.setLayout(new GridLayout(2,1));
        title.setBackground(Model.BackGroundColor);
        day.setForeground(Model.TextColor);
        date.setForeground(Model.TextColor);
        TaskContainer morningContainer = new TaskContainer(morning);
        TaskContainer noonContainer = new TaskContainer(noon);
        TaskContainer afterNoonContainer = new TaskContainer(afterNoon);
        TaskContainer nightContainer = new TaskContainer(night);
        
        day.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        day.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        date.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        date.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        title.setBorder(Model.basicBorder);
        
        title.add(day);
        title.add(date);
        this.add(title);
        this.add(morningContainer);
        this.add(noonContainer);
        this.add(afterNoonContainer);
        this.add(nightContainer);
 
    }
    private void buildMonth(String dayString, String dateString){
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        JLabel day = new JLabel(dayString);
        JLabel date = new JLabel(dateString);
        day.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        day.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        day.setForeground(Model.TextColor);
        date.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        date.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        date.setForeground(Model.TextColor);
        this.setBorder(Model.basicBorder);
        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1;
        gc.gridy = 0;
        this.add(date, gc);
        gc.gridy = 1;
        JLabel separator = new JLabel();
        separator.setBorder(Model.basicBorder);
        this.add(separator, gc);
        gc.gridy = 2;
        this.add(day, gc);
        if (!tasks.isEmpty()) {
            JButton showTasks = new JButton(String.valueOf(tasks.size()));
            showTasks.setBackground(Model.PrimaryColor);
            showTasks.setForeground(Model.TextColor);
            showTasks.addActionListener(e -> monthButtonClick(dateString + " - " + dayString, tasks)); 
            gc.weighty = 1;
            gc.gridy = 3;
            this.add(showTasks, gc);
        }else {
            JPanel noTasks = new JPanel();
            JLabel lbl = new JLabel("No tasks");
            noTasks.setBackground(Model.BackGroundColor);
            lbl.setForeground(Model.TextColor);
            gc.weighty = 1;
            gc.gridy = 3;
            noTasks.add(lbl);
            this.add(noTasks, gc);
        }
    }

    private void monthButtonClick(String title, List<Task> tasks){
        JDialog tasksOfDay = new JDialog();
        tasksOfDay.getContentPane().setBackground(Model.BackGroundColor);
        tasksOfDay.getContentPane().setForeground(Model.TextColor);
        tasksOfDay.setTitle(title);
        JTable tableOfTasks = new JTable(new TaskTableModel(tasks));
        JScrollPane scroll = new JScrollPane(tableOfTasks);
        scroll.setBackground(Model.BackGroundColor);
        scroll.setForeground(Model.TextColor);
        tableOfTasks.setForeground(Model.TextColor);
        tableOfTasks.setBackground(Model.BackGroundColor);
        scroll.getViewport().setBackground(Model.BackGroundColor);
        
        tasksOfDay.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); 
        tasksOfDay.add(scroll);
        tasksOfDay.pack();
        tasksOfDay.setVisible(true);
        tasksOfDay.setResizable(false);
    }

}
