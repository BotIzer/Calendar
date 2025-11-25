package components;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

import models.Model;
import models.Task;
import models.TaskTableModel;
import views.WindowBase.View;

//Container for day in table
public class DayContainer extends JPanel{
    private ArrayList<Task> tasks;
    public DayContainer(String dayString, String dateString, List<Task> dayTasks, View v){
        this.setBackground(Model.BackGround);
        tasks = new ArrayList<>(dayTasks);
        if (v == View.WEEK) buildWeek(dayString, dateString);
        if (v == View.MONTH) buildMonth(dayString, dateString);
        if (v == View.YEAR) ;//TODO buildyear;
        
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
        title.setBackground(Model.BackGround);
        day.setForeground(Color.WHITE);
        date.setForeground(Color.WHITE);
        TaskContainer morningContainer = new TaskContainer(morning);
        TaskContainer noonContainer = new TaskContainer(noon);
        TaskContainer afterNoonContainer = new TaskContainer(afterNoon);
        TaskContainer nightContainer = new TaskContainer(night);
        
        day.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        day.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        date.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        date.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        title.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
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
        date.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        date.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1;
        gc.gridy = 0;
        this.add(date, gc);
        gc.gridy = 1;
        JLabel separator = new JLabel();
        separator.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(separator, gc);
        gc.gridy = 2;
        this.add(day, gc);
        if (!tasks.isEmpty()) {
            JButton showTasks = new JButton(String.valueOf(tasks.size()));
            showTasks.addActionListener(e -> monthButtonClick(dateString + " - " + dayString, tasks)); 
            gc.weighty = 1;
            gc.gridy = 3;
            this.add(showTasks, gc);
        }
    }

    private void monthButtonClick(String title, List<Task> tasks){
        JDialog tasksOfDay = new JDialog();
        tasksOfDay.setTitle(title);
        JTable tableOfTasks = new JTable(new TaskTableModel(tasks));
        JScrollPane scroll = new JScrollPane(tableOfTasks);
        tasksOfDay.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); 
        tasksOfDay.add(scroll);
        tasksOfDay.pack();
        tasksOfDay.setVisible(true);
        tasksOfDay.setResizable(false);
    }

}
