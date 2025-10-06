package src.main.java.windows;


import java.awt.Color;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.border.Border;

import src.main.java.Models.Task;
//Dialog popup for tasks and modifications
public class Details extends JDialog {
    //Input fields to get updated data from, need to access in onClick function
    static JTextPane newTitle = new JTextPane();
    static JTextPane newDesc = new JTextPane();
    static JCheckBox newPriority = new JCheckBox();
    static JFormattedTextField newStart = new JFormattedTextField(new SimpleDateFormat(WindowBase.dateOutFormat));
    static JFormattedTextField newEnd = new JFormattedTextField(new SimpleDateFormat(WindowBase.dateOutFormat));

    public Details(Task task){
        //Disable parent until dialog is closed
        WindowBase.getInstance().setEnabled(false);
        //Configuration of layout
        this.setLayout(new GridLayout(6, 2, 0,0));
        this.setName("Details");
        this.setSize(250, 400);
        this.setTitle("Details");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //Components in first column
        JLabel title = new JLabel("Title:");
        JLabel desc = new JLabel("Description:");
        JLabel start = new JLabel("Start time:");
        JLabel end = new JLabel("End time:");
        JLabel priority = new JLabel("Priority:");
        //Components in second column
        newTitle.setText(task.getTitle());
        newDesc.setText(task.getDescription());
        newPriority.setSelected(task.getPriority());
        newStart.setText(task.getStart().format(DateTimeFormatter.ofPattern(WindowBase.dateOutFormat)));
        newEnd.setText(task.getEnd().format(DateTimeFormatter.ofPattern(WindowBase.dateOutFormat)));
        JButton ok = new JButton("Ok");
        
        //Styling
        Border border = BorderFactory.createLineBorder(Color.black);
        newTitle.setBorder(border);
        newDesc.setBorder(border);
        ok.setAlignmentY(BOTTOM_ALIGNMENT);
        //Eventlisteners
        ok.addActionListener(e -> onClick(task));
        //Adding individual components
        this.add(title);
        this.add(newTitle);
        this.add(desc);
        this.add(newDesc);
        this.add(start);
        this.add(newStart);
        this.add(end);
        this.add(newEnd);
        this.add(priority);
        this.add(newPriority);
        this.add(ok);
        this.setVisible(true);
        
    }
    //Save changes,then close window and enable parent
    private void onClick(Task task){
        try {
            task.setTitle(newTitle.getText());
            task.setDesc(newDesc.getText());
            task.setStart(Task.stringToLocalDate(newStart.getText()));
            task.setEnd(Task.stringToLocalDate(newEnd.getText()));
            task.setPrio(newPriority.isSelected());
            WindowBase.getInstance().setEnabled(true);
            dispose();
        } catch (Exception e) {
            JDialog errDialog = new JDialog();
            errDialog.setLayout(new GridLayout(2,1));
            JLabel errLabel = new JLabel(e.getMessage());
            JButton ok = new JButton("Ok");
            ok.addActionListener(a -> {this.setEnabled(true); errDialog.dispose();});
            errDialog.setSize(errLabel.getPreferredSize().width + 100, 100);
            errDialog.setTitle("Error");
            errDialog.add(errLabel);
            errDialog.add(ok);
            errDialog.setVisible(true);
            errDialog.setAlwaysOnTop(true);
            //Only closable via button because i dont know how to call function
            errDialog.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            this.setEnabled(false);
        } 
    }
    //Enable Parent even if force closed via statusbar, discards changes
    @Override
    public void dispose() {
        super.dispose();
        WindowBase.getInstance().setEnabled(true);
        WindowBase.getInstance().toFront();
    }
}
