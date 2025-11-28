package main.java.components;


import java.awt.Color;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import main.java.models.Model;
import main.java.models.Task;
import main.java.views.WindowBase;

//Context menu for task
public class ContextMenu extends JMenu{
    public ContextMenu(Task task){
        this.setText(task.getTitle());
        this.setForeground(Model.TextColor);
        JMenuItem edit = new JMenuItem("Edit");
        JMenuItem delete = new JMenuItem("Delete");
        edit.addActionListener(e -> new Details(task));
        delete.addActionListener(e -> {Model.tasks.remove(task);
                                       WindowBase.getInstance().refresh();
        });
        this.setOpaque(true);
        this.setBackground(task.getPriority() ? Color.RED : Model.PrimaryColor);
        this.add(edit);
        this.add(delete);
    }
}
