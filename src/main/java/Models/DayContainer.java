package src.main.java.Models;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DayContainer extends JPanel{
    public DayContainer(String label, ArrayList<Task> tasks){
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

        JLabel title = new JLabel(label);
        TaskContainer morningContainer = new TaskContainer(morning);
        TaskContainer noonContainer = new TaskContainer(noon);
        TaskContainer afterNoonContainer = new TaskContainer(afterNoon);
        TaskContainer nightContainer = new TaskContainer(night);

        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        title.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        this.add(title);
        this.add(morningContainer);
        this.add(noonContainer);
        this.add(afterNoonContainer);
        this.add(nightContainer);
    }
}
