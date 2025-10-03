package src.main.java.Models;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.main.java.windows.Details;
import src.main.java.windows.WindowBase;

public class TaskContainer extends JPanel{
    private static JButton tb;
    public TaskContainer(ArrayList<Task> tl){
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        tb = new JButton();
        tb.setFocusable(false);
        if (tl.size() > 2) {
            gc.gridwidth = 2;
            gc.fill = GridBagConstraints.BOTH;
            gc.weighty = 1;
            gc.weightx = 1;
            tb.setText((tl.get(0).getTitle()));
            tb.addActionListener(e -> new Details(tl.get(0)));
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
                tb.setText(task.getTitle());
                tb.addActionListener(e -> new Details(task));
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
