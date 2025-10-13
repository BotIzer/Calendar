package src.main.java.models;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import src.main.java.windows.Details;


public class TaskContainer extends JPanel{
    //Argument is the list of tasks for current time of day (morning/noon/afternoon/night)
    public TaskContainer(List<Task> tl){

        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        //Button for individual tasks, shows details dialog on click
        JButton tb = new JButton();
        tb.setFocusable(false);
        if (tl.size() >= 2) {
            //Layout configuration if there are multiple tasks
            gc.gridwidth = 2;
            gc.fill = GridBagConstraints.BOTH;
            gc.weighty = 1;
            gc.weightx = 1;
            tb.setText((tl.get(0).getTitle()));
            //Listener for task button, shows details dialog
            tb.addActionListener(e -> new Details(tl.get(0)));
            //Priority Styling
            if (tl.get(0).getPriority()) {
                tb.setBackground(Color.RED);
            } else {
                tb.setBackground(Color.CYAN);
            }
            this.add(tb, gc);
            //"Dropdown menu" if there are multiple tasks that would not fit otherwise
            JMenuBar menuBar = new JMenuBar();
            JMenu showMore = new JMenu("+" + (tl.size() - 1));
            for (Task task : tl.subList(1, tl.size())) {
                //Loop through tasks and create a menu item for each, that call the dialog popup
                JMenuItem taskMenu = new JMenuItem(task.getTitle());
                showMore.add(taskMenu);
                taskMenu.addActionListener(e -> new Details(task));
            }
            //Allocate all leftover space to task button
            gc.weightx = 0;
            menuBar.add(showMore);
            this.add(menuBar, gc);
        }else {
            //Layout for single task slots
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
