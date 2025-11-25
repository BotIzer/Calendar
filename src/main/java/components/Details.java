package components;


import java.awt.Color;
import java.awt.GridLayout;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.Border;

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
    JComboBox<Integer> startMinute = new JComboBox<Integer>();
    JComboBox<Integer> startHour = new JComboBox<Integer>();
    JComboBox<Integer> durationMinute = new JComboBox<Integer>();
    JComboBox<Integer> durationHour = new JComboBox<Integer>();


    public Details(Task task){
        WindowBase.getInstance().setEnabled(false);
        this.setLayout(new GridLayout(7, 2, 5,5));
        this.setName("Details");
        this.setTitle("Details");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JLabel title = new JLabel("Title:");
        JLabel desc = new JLabel("Description:");
        JLabel start = new JLabel("Start date:");
        JLabel startTimeLbl = new JLabel("Start time (HH-mm)");
        JLabel durationLbl = new JLabel("Duration: (H-m)");
        JLabel priority = new JLabel("Priority:");
        newStart = new JDateChooser(Date.from(task.getStart().atZone(ZoneId.systemDefault()).toInstant()), Model.getDateFormat());
        newStart.setMinSelectableDate(Date.from(task.getStart().atZone(ZoneId.systemDefault()).toInstant()));
        
        newTitle.setText(task.getTitle());
        newDesc.setText(task.getDescription());
        newPriority.setSelected(task.getPriority());
        JPanel duration = new JPanel(new GridLayout(1, 2));
        JPanel startTime = new JPanel(new GridLayout(1, 2)); 
        Integer[] tmp = new Integer[25];
        for (int i = 0; i < 25; i++) {
            tmp[i] = Integer.valueOf(i);
        }
        durationHour = new JComboBox<Integer>(tmp);
        startHour = new JComboBox<Integer>(tmp);
        tmp = new Integer[61];
        for (int i = 0; i < 61; i++) {
            tmp[i] = Integer.valueOf(i);
        }
        durationMinute = new JComboBox<Integer>(tmp);
        startMinute = new JComboBox<Integer>(tmp);
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

        Border border = BorderFactory.createLineBorder(Color.black);
        newTitle.setBorder(border);
        newDesc.setBorder(border);
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
