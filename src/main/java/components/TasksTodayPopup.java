package main.java.components;

import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import main.java.models.Model;
import main.java.models.Task;
import main.java.models.TaskTableModel;

public class TasksTodayPopup extends JDialog {
    public TasksTodayPopup(List<Task> tl){
        this.setTitle("There are #" + tl.size() + " tasks today");
        this.setBackground(Model.BackGroundColor);
        this.setForeground(Model.TextColor);
        
        JTable table = new JTable(new TaskTableModel(tl));
        table.setBackground(Model.BackGroundColor);
        table.setForeground(Model.TextColor);

        this.add(table);
        this.pack();
        this.setVisible(true);
        this.toFront();
    }
}
