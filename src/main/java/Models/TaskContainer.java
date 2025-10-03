package src.main.java.Models;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TaskContainer extends JPanel{
    public TaskContainer(ArrayList<Task> tl){
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        
        if (tl.size() > 3) {
            gc.gridwidth = 2;
            gc.fill = GridBagConstraints.BOTH;
            gc.weighty = 1;
            gc.weightx = 1;
            JButton tb = new JButton(tl.get(0).getTitle());
            JButton showMore = new JButton("+" + tl.size());
            if (tl.get(0).getPriority()) {
                tb.setBackground(Color.RED);
            } else {
                tb.setBackground(Color.CYAN);
            }
            this.add(tb, gc);
            gc.weightx = 0;
            this.add(showMore, gc);
        }else {
            gc.gridwidth = tl.size();
            gc.fill = GridBagConstraints.BOTH;
            gc.weighty = 1;
            gc.weightx = 1;
            for (Task task : tl) {
                JButton tb = new JButton(task.getTitle());
                if (task.getPriority()) {
                    tb.setBackground(Color.RED);
                } else {
                    tb.setBackground(Color.CYAN);
                }

                this.add(tb, gc);
            }   
        }
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    }
}
