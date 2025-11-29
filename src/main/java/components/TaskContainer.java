package main.java.components;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import main.java.models.Model;
import main.java.models.Task;


//Container that displays a task, or list if there are multiple that wouldnt fit
public class TaskContainer extends JPanel{
    public TaskContainer(List<Task> tl){
        this.setBackground(Model.BackGroundColor);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        if (tl.size() >= 2) {
            JMenuBar container = new JMenuBar();
            container.setLayout(new GridBagLayout());
            GridBagConstraints menugc = new GridBagConstraints();
            menugc.fill = GridBagConstraints.BOTH;
            menugc.weighty = 1;
            menugc.weightx = 1;
            menugc.gridx = 0;
            ContextMenu context = new ContextMenu(tl.get(0));
            if (tl.get(0).getPriority()) {
                container.setBackground(Model.PrimaryColor);
            } else {
                container.setBackground(Model.SecondaryColor);
            }
            JMenu showMore = new JMenu("+" + (tl.size() - 1));
            for (Task task : tl.subList(1, tl.size())) {
                ContextMenu subContext = new ContextMenu(task);
                showMore.add(subContext);
            }
            container.setBorder(null);
            container.setBorderPainted(false); 
            gc.fill = GridBagConstraints.BOTH;
            gc.weightx = 1;
            gc.weighty = 1;
            container.add(context, menugc);
            menugc.gridx = 1;
            menugc.weightx = 0;
            showMore.setBackground(Color.LIGHT_GRAY);
            showMore.setOpaque(true);
            showMore.setBackground(Color.LIGHT_GRAY);
            context.setBackground(Model.PrimaryColor);
            container.add(showMore, menugc);
            this.add(container, gc);
        }else if(!tl.isEmpty()){
            gc.fill = GridBagConstraints.BOTH;
            gc.weighty = 1;
            gc.weightx = 1;
            for (Task task : tl) {
                JMenuBar container = new JMenuBar();
                container.setLayout(new GridLayout(1,1));
                container.setBorder(null);
                container.setBorderPainted(false);
                ContextMenu context = new ContextMenu(task);
                context.setAlignmentX(CENTER_ALIGNMENT);
                container.add(context);
                this.add(container,gc);
            }   
        }
        this.setBorder(Model.basicBorder);
    }
}
