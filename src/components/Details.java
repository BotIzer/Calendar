package src.components;


import java.awt.Color;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.border.Border;

import com.toedter.calendar.JDateChooser;

import models.Model;
import models.Task;
import views.WindowBase;

//Dialog popup for tasks and modifications
public class Details extends JDialog {
    //Input fields to get updated data from, need to access in onClick function
    static JTextPane newTitle = new JTextPane();
    static JTextPane newDesc = new JTextPane();
    static JCheckBox newPriority = new JCheckBox();
    static JDateChooser newStart ;//new JFormattedTextField(new SimpleDateFormat(WindowBase.dateOutFormat));
    static JComboBox startMinute = new JComboBox<Integer>();//new JFormattedTextField(new SimpleDateFormat(WindowBase.dateOutFormat));
    static JComboBox startHour = new JComboBox<Integer>();//new JFormattedTextField(new SimpleDateFormat(WindowBase.dateOutFormat));
    static JComboBox durationMinute = new JComboBox<Integer>();//new JFormattedTextField(new SimpleDateFormat(WindowBase.dateOutFormat));
    static JComboBox durationHour = new JComboBox<Integer>();//new JFormattedTextField(new SimpleDateFormat(WindowBase.dateOutFormat));



    public Details(Task task){
        //Disable parent until dialog is closed
        WindowBase.getInstance().setEnabled(false);
        //Configuration of layout
        this.setLayout(new GridLayout(7, 2, 5,5));
        this.setName("Details");
        this.setTitle("Details");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //Components in first column
        JLabel title = new JLabel("Title:");
        JLabel desc = new JLabel("Description:");
        JLabel start = new JLabel("Start date:");
        JLabel startTimeLbl = new JLabel("Start time (HH-mm)");
        JLabel durationLbl = new JLabel("Duration: (H-m)");
        JLabel priority = new JLabel("Priority:");
        newStart = new JDateChooser(Date.from(task.getStart().atZone(ZoneId.systemDefault()).toInstant()), Model.getDateFormat());
        newStart.setMinSelectableDate(Date.from(task.getStart().atZone(ZoneId.systemDefault()).toInstant()));
        
        //Components in second column
        newTitle.setText(task.getTitle());
        newDesc.setText(task.getDescription());
        newPriority.setSelected(task.getPriority());
        JPanel duration = new JPanel(new GridLayout(1, 2));
        JPanel startTime = new JPanel(new GridLayout(1, 2)); 
        Integer[] tmp = new Integer[24];
        for (int i = 1; i < 25; i++) {
            tmp[i-1] = Integer.valueOf(i);
        }
        durationHour = new JComboBox<Integer>(tmp);
        startHour = new JComboBox<Integer>(tmp);
        tmp = new Integer[60];
        for (int i = 1; i < 61; i++) {
            tmp[i-1] = Integer.valueOf(i);
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

        //Styling
        Border border = BorderFactory.createLineBorder(Color.black);
        newTitle.setBorder(border);
        newDesc.setBorder(border);
        close.setAlignmentY(BOTTOM_ALIGNMENT);
        //Eventlisteners
        close.addActionListener(e -> {this.dispose();
                                   WindowBase.getInstance().setEnabled(true);});
        save.addActionListener(e -> onClick(task));
        //Adding individual components
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
            String tmp = LocalDateTime.ofInstant(newStart.getDate().toInstant(), ZoneId.systemDefault()).toString();
            tmp = tmp.split("[T]")[0];
            tmp += "T";
            tmp += (Integer)startHour.getSelectedItem() < 10 ? "0" + ((Integer)startHour.getSelectedItem()).toString() : ((Integer)startHour.getSelectedItem()).toString();
            tmp += ":";
            tmp += (Integer)startMinute.getSelectedItem() < 10 ? "0" + ((Integer)startMinute.getSelectedItem()).toString() : ((Integer)startMinute.getSelectedItem()).toString();
            LocalDateTime tmpDate = LocalDateTime.parse(tmp);
            task.setStart(tmpDate, LocalDateTime.now());
            task.setDuration(new Integer[]{(Integer)durationHour.getSelectedItem(), (Integer)durationMinute.getSelectedItem()});
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
