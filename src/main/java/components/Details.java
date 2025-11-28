package components;


import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import com.toedter.calendar.JDateChooser;

import models.Model;
import models.Task;
import views.WindowBase;

//Dialog popup for tasks and modifications
//Shows a popup menu with data of task, disables parent until closed
public class Details extends JDialog {
    JTextPane newTitle = new JTextPane();
    JTextPane newDesc = new JTextPane();
    JCheckBox newPriority = new JCheckBox();
    JDateChooser newStart;
    JComboBox<Integer> startMinute = new JComboBox<>();
    JComboBox<Integer> startHour = new JComboBox<>();
    JComboBox<Integer> durationMinute = new JComboBox<>();
    JComboBox<Integer> durationHour = new JComboBox<>();


    public Details(Task task){
        WindowBase.getInstance().setEnabled(false);
        this.setLayout(new GridLayout(7, 2, 5,5));
        this.setName("Details");
        this.setTitle("Details");
        this.getContentPane().setBackground(Model.BackGroundColor);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JLabel title = new JLabel("Title:");
        JLabel desc = new JLabel("Description:");
        JLabel start = new JLabel("Start date:");
        JLabel startTimeLbl = new JLabel("Start time (HH-mm)");
        JLabel durationLbl = new JLabel("Duration: (H-m)");
        JLabel priority = new JLabel("Priority:");
        newStart = new JDateChooser(Date.from(task.getStart().atZone(ZoneId.systemDefault()).toInstant()), Model.getDateFormat());
        newStart.setMinSelectableDate(Date.from(Model.now.atZone(ZoneId.systemDefault()).toInstant()));
        newTitle.setText(task.getTitle());
        newDesc.setText(task.getDescription());
        newPriority.setSelected(task.getPriority());
        JPanel duration = new JPanel(new GridLayout(1, 2));
        JPanel startTime = new JPanel(new GridLayout(1, 2)); 
        Integer[] tmp = new Integer[25];
        for (int i = 0; i < 25; i++) {
            tmp[i] = Integer.valueOf(i);
        }
        durationHour = new JComboBox<>(tmp);
        startHour = new JComboBox<>(tmp);
        tmp = new Integer[60];
        for (int i = 0; i < 60; i++) {
            tmp[i] = Integer.valueOf(i);
        }
        durationMinute = new JComboBox<>(tmp);
        startMinute = new JComboBox<>(tmp);
        durationHour.setSelectedItem(task.getDuration()[0]);
        durationMinute.setSelectedItem(task.getDuration()[1]);
        startHour.setSelectedItem(Integer.valueOf(task.getStart().toString().substring(11,13)));
        startMinute.setSelectedItem(Integer.valueOf(task.getStart().toString().substring(14, 16)));
        duration.add(durationHour);
        duration.add(durationMinute);
        startTime.add(startHour);
        startTime.add(startMinute);
        JButton close = new JButton("Close");
        JButton save = new JButton("Save");

        newTitle.setBorder(Model.basicBorder);
        newDesc.setBorder(Model.basicBorder);
        close.setAlignmentY(BOTTOM_ALIGNMENT);
        
        close.addActionListener(e -> {
                                        this.dispose();
                                        WindowBase.getInstance().setEnabled(true);
                                     });
        save.addActionListener(e -> onClick(task));
        
        this.add(title);
        this.add(newTitle);
        this.add(desc);
        this.add(newDesc);
        this.add(start);
        this.add(newStart);
        this.add(startTimeLbl);
        this.add(startTime);
        this.add(durationLbl);
        this.add(duration);
        this.add(priority);
        this.add(newPriority);
        this.add(close);
        this.add(save);
        this.setVisible(true);
        
        for (Component c : this.getContentPane().getComponents()) {
            c.setForeground(Model.TextColor);
            c.setBackground(Model.BackGroundColor);
        }
        newTitle.setBackground(Color.WHITE);
        newTitle.setForeground(Color.BLACK);
        newDesc.setBackground(Color.WHITE);
        newDesc.setForeground(Color.BLACK);
        save.setBackground(Model.PrimaryColor);
        close.setBackground(Model.SecondaryColor);
        this.pack();
    }
    //Save changes,then close window and enable parent
    private void onClick(Task task){
        try {
            task.setTitle(newTitle.getText());
            task.setDesc(newDesc.getText());
            task.setDuration(new Integer[]{(Integer)durationHour.getSelectedItem(), (Integer)durationMinute.getSelectedItem()});
            task.setStart(Task.makeDate(newStart.getDate(), new Integer[]{(Integer)startHour.getSelectedItem(),(Integer)startMinute.getSelectedItem()}), LocalDateTime.now());
            task.setPrio(newPriority.isSelected());
            
            
            WindowBase.getInstance().setEnabled(true);
            Task.setTask(task);
            WindowBase.getInstance().refresh();
            dispose();
        } catch (Exception e) {
            JDialog errDialog = new JDialog();
            errDialog.setLayout(new GridLayout(2,1));
            JLabel errLabel = new JLabel(e.getMessage());
            JButton ok = new JButton("Ok");
            ok.addActionListener(ev -> {this.setEnabled(true); errDialog.dispose();});
            errDialog.setSize(errLabel.getPreferredSize().width + 100, 100);
            errDialog.setTitle("Error");
            errDialog.add(errLabel);
            errDialog.add(ok);
            errDialog.setVisible(true);
            errDialog.setAlwaysOnTop(true);
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
