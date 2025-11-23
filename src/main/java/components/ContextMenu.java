package components;

import java.awt.Color;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import models.Model;
import models.Task;
import views.WindowBase;

public class ContextMenu extends JMenu{
    public ContextMenu(Task task){
        this.setText(task.getTitle());
        JMenuItem edit = new JMenuItem("Edit");
        JMenuItem delete = new JMenuItem("Delete");
        edit.addActionListener(e -> new Details(task));
        delete.addActionListener(e -> {Model.tasks.remove(task);
                                       WindowBase.getInstance().refresh();
        });
        this.add(edit);
        this.add(delete);
    }
}
