package components;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import models.Task;


public class TaskContainer extends JPanel{
    //Argument is the list of tasks for current time of day (morning/noon/afternoon/night)
    public TaskContainer(List<models.Task> tl){
        //TODO Style ContextMenus via custom menubar
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        if (tl.size() >= 2) {
            JMenuBar container = new JMenuBar();
            container.setLayout(new GridLayout(1,2));
            //Layout configuration if there are multiple tasks
            ContextMenu context = new ContextMenu(tl.get(0));
            //Priority Styling
            if (tl.get(0).getPriority()) {
                container.setBackground(Color.RED);
            } else {
                container.setBackground(Color.CYAN);
            }
            gc.fill = GridBagConstraints.BOTH;
            gc.weightx = 1;
            gc.weighty = 1;
            container.add(context);
            //"Dropdown menu" if there are multiple tasks that would not fit otherwise
            JMenu showMore = new JMenu("+" + (tl.size() - 1));
            for (Task task : tl.subList(1, tl.size())) {
                //Loop through tasks and create a contextmenu for each
                ContextMenu subContext = new ContextMenu(task);
                showMore.add(subContext);
            }
            //Allocate all leftover space to task button
            container.setOpaque(false); 
            container.add(showMore);
            this.add(container, gc);
        }else if(!tl.isEmpty()){
            //Layout for single task slots
            gc.fill = GridBagConstraints.BOTH;
            gc.weighty = 1;
            gc.weightx = 1;
            for (Task task : tl) {
                JMenuBar container = new JMenuBar();
                container.setLayout(new GridLayout(1,1));
                ContextMenu context = new ContextMenu(task);
                context.setAlignmentX(CENTER_ALIGNMENT);
                if (task.getPriority()) {
                    container.setBackground(Color.RED);
                } else {
                    container.setBackground(Color.CYAN);
                }
                container.add(context);
                this.add(container,gc);
            }   
        }
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    }
}
