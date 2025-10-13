package src.main.java.models;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DayContainer extends JPanel{
    //Container for a day
    public DayContainer(String label, java.util.List<Task> tasks){
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
        JLabel title = new JLabel(label);
        TaskContainer morningContainer = new TaskContainer(morning);
        TaskContainer noonContainer = new TaskContainer(noon);
        TaskContainer afterNoonContainer = new TaskContainer(afterNoon);
        TaskContainer nightContainer = new TaskContainer(night);
        //Styling
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        title.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        //Add components to Container
        this.add(title);
        this.add(morningContainer);
        this.add(noonContainer);
        this.add(afterNoonContainer);
        this.add(nightContainer);
    }
}
