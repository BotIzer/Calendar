package components;

import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import models.Model;


public class CustomClock extends JPanel{
    protected JLabel date = new JLabel();
    protected JLabel time = new JLabel();
    public CustomClock(){
        this.setLayout(new GridLayout(2,1));
        date = new JLabel(Model.now.format(DateTimeFormatter.ofPattern(Model.getDateFormat())));
        time = new JLabel(Model.now.format(DateTimeFormatter.ofPattern(Model.getTimeFormat())));
        Timer timer = new Timer(100, e -> refresh());

        date.setHorizontalAlignment(JLabel.CENTER);
        date.setVerticalAlignment(JLabel.CENTER);
        date.setFont(new Font("Times New Roman", Font.BOLD, 100));
        time.setHorizontalAlignment(JLabel.CENTER);
        time.setVerticalAlignment(JLabel.CENTER);
        time.setFont(new Font("Times New Roman", Font.BOLD, 80));

        timer.start();
        this.add(date);
        this.add(time);
    }
    
    public void refresh(){
        Model.now = LocalDateTime.now();
        date.setText(Model.now.format(DateTimeFormatter.ofPattern(Model.getDateFormat())));
        time.setText(Model.now.format(DateTimeFormatter.ofPattern(Model.getTimeFormat())));
        this.getParent().repaint();
    }
}

