package components;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import models.Task;
import views.WindowBase.View;


public class DayContainer extends JPanel{
    private ArrayList<Task> tasks;
    //Container for a day
    public DayContainer(String dayString, String dateString, List<Task> dayTasks, View v){
        tasks = new ArrayList<>(dayTasks);
        if (v == View.WEEK) buildWeek(dayString, dateString);
        if (v == View.MONTH) buildMonth(dayString, dateString);
        if (v == View.YEAR) ;//TODO buildyear;
        
   }
    private void buildWeek(String dayString, String dateString){
        //Layout configuration
        this.setLayout(new GridLayout(5, 1));
        //Divide task to timeslots
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
        //Component declaration
        JLabel day = new JLabel(dayString);
        JLabel date = new JLabel(dateString);
        JPanel title = new JPanel();
        title.setLayout(new GridLayout(2,1));
        TaskContainer morningContainer = new TaskContainer(morning);
        TaskContainer noonContainer = new TaskContainer(noon);
        TaskContainer afterNoonContainer = new TaskContainer(afterNoon);
        TaskContainer nightContainer = new TaskContainer(night);
        //Styling
        day.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        day.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        date.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        date.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        title.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        //Add components to Container
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
        gc.fill = gc.BOTH;
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
            gc.weighty = 1;
            gc.gridy = 3;
            this.add(showTasks, gc);
        }
    }

}
