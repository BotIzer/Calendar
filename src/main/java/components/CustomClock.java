package main.java.components;

import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import main.java.models.Model;


//Dynamically changing clock, refreshes every 100 ms
public class CustomClock extends JPanel{
    protected JLabel date = new JLabel();
    protected JLabel time = new JLabel();
    public CustomClock(){
        this.setLayout(new GridLayout(2,1));
        this.setBackground(Model.BackGroundColor);
        date = new JLabel(Model.now.format(DateTimeFormatter.ofPattern(Model.getDateFormat())));
        time = new JLabel(Model.now.format(DateTimeFormatter.ofPattern(Model.getTimeFormat())));
        Timer timer = new Timer(100, e -> refresh());

        date.setHorizontalAlignment(SwingConstants.CENTER);
        date.setVerticalAlignment(SwingConstants.CENTER);
        date.setFont(new Font("Times New Roman", Font.BOLD, 100));
        date.setForeground(Model.TextColor);
        time.setHorizontalAlignment(SwingConstants.CENTER);
        time.setVerticalAlignment(SwingConstants.CENTER);
        time.setFont(new Font("Times New Roman", Font.BOLD, 80));
        time.setForeground(Model.TextColor);

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

