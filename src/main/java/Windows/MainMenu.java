package src.main.java.Windows;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.Border;
import java.awt.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Dictionary;
import java.util.Locale;

public class MainMenu extends WindowBase {
    public MainMenu() {
        this.setSize(_resolution);
        this.setTitle("Calendar");
        
        this.setLayout(new GridLayout(3, 1, 20, 20));

        JLabel title = new JLabel("Calendar");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.TOP);
        
        Container calendar = new Container();
        
        calendar.setLayout(new GridLayout(1,7));
        for (int i = 1; i < 8; i++) {
            JLabel name = new JLabel(DayOfWeek.of(i).getDisplayName(TextStyle.FULL, getLocale()));
            name.setVerticalAlignment(JLabel.TOP);
            name.setHorizontalAlignment(JLabel.CENTER);
            name.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            calendar.add(name);
        }
        this.add(title);
        this.add(calendar);
        
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
        
}
