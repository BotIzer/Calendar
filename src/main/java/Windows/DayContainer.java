package src.main.java.Windows;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import src.main.java.Models.*;

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
        JPanel morningPanel = new JPanel(new GridBagLayout());
        JPanel noonPanel = new JPanel(new GridBagLayout());
        JPanel afterNoonPanel = new JPanel(new GridBagLayout());
        JPanel nightPanel = new JPanel(new GridBagLayout());


        this.add(title);

        GridBagConstraints gc = new GridBagConstraints();
        gc.gridwidth = morning.size();
        for (Task task : morning) {
            morningPanel.add(new JLabel(task.getTitle()));
        }
        this.add(morningPanel, gc);

        gc.gridwidth = noon.size();
        for (Task task : noon) {
            noonPanel.add(new JLabel(task.getTitle()));
        }
        this.add(noonPanel, gc);

        gc.gridwidth = afterNoon.size();
        for (Task task : afterNoon) {
            afterNoonPanel.add(new JLabel(task.getTitle()));
        }
        this.add(afterNoonPanel, gc);

        gc.gridwidth = night.size();
        for (Task task : night) {
            nightPanel.add(new JLabel(task.getTitle()));
        }
        morningPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 5));
        noonPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 5));
        afterNoonPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 5));
        nightPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 5));
        this.add(nightPanel, gc);
    }
}
