package src.main.java.windows;


import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.text.NumberFormat;
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

public class Details extends JDialog {
    public Details(Task tl){
        //Disable parent until dialog is closed
        WindowBase.getInstance().setEnabled(false);
        this.setLayout(new GridLayout(6, 2, 0,0));
        this.setName("Details");
        this.setSize(250, 400);
        this.setTitle("Details");
        //Components in first column
        JLabel title = new JLabel("Title:");
        JLabel desc = new JLabel("Description:");
        JLabel start = new JLabel("Start time:");
        JLabel end = new JLabel("End time:");
        JLabel priority = new JLabel("Priority:");
        //Components in second column
        JTextPane newTitle = new JTextPane();
        newTitle.setText(tl.getTitle());
        JTextPane newDesc = new JTextPane();
        newDesc.setText(tl.getDescription());
        JCheckBox newPriority = new JCheckBox();
        newPriority.setSelected(tl.getPriority());
        JFormattedTextField newStart = new JFormattedTextField(new SimpleDateFormat("yyyy.MM.dd HH:mm"));
        newStart.setText(tl.getStart().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")));
        JFormattedTextField newEnd = new JFormattedTextField(new SimpleDateFormat("yyyy.MM.dd HH:mm"));
        newEnd.setText(tl.getEnd().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")));
        JButton ok = new JButton("Ok");
        
        //Styling
        Border border = BorderFactory.createLineBorder(Color.black);
        newTitle.setBorder(border);
        newDesc.setBorder(border);
        ok.setAlignmentY(BOTTOM_ALIGNMENT);

        ok.addActionListener(e -> onClick());
        
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
        
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    //Save changes,then close window and enable parent
    private void onClick(){
        WindowBase.getInstance().setEnabled(true);
        dispose();
    }
    //Enable Parent even if force closed
    @Override
    public void dispose() {
        super.dispose();
        WindowBase.getInstance().setEnabled(true);
        WindowBase.getInstance().toFront();
    }
}
