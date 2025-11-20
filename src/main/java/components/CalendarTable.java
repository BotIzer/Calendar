package components;

import java.awt.Color;
import java.awt.GridLayout;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.Model;
import models.Task;
import views.WindowBase.View;



public class CalendarTable extends JPanel{
    
    protected View view = View.WEEK;
    
    public CalendarTable(View v){
        if(v == View.WEEK) buildWeek();
        if (v == View.MONTH) buildMonth();
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
    }
    private void buildWeek(){
        this.setLayout(new GridLayout(1, 7));
        int date = Model.now.getDayOfYear(); 
        for (int i = date - 3; i < date + 4; i++) {
            ArrayList<Task> dayList = new ArrayList<>();
            for (Task task : Model.tasks) {
                if (task.getStart().getDayOfYear() == i) {
                    dayList.add(task);
                }
            }
            String day = Model.now.plusDays(i-date).getDayOfWeek().getDisplayName(TextStyle.FULL, getLocale());
            String dateString = Model.now.plusDays(i-date).format(DateTimeFormatter.ofPattern(Model.getDateFormat()));
            DayContainer dc = new DayContainer(day, dateString, dayList, View.WEEK);
            this.add(dc);
        }
    };
    //TODO implement and buildYear
    private void buildMonth(){
        this.setLayout(new GridLayout(5, 7));
        for (int i = 0; i < 31; i++) {
            ArrayList<Task> dayList = new ArrayList<>();
            for (Task task : Model.tasks) {
                if (task.getStart().getDayOfYear() == Model.now.plusDays(i).getDayOfYear()) {
                    dayList.add(task);
                }
            }
            String day = Model.now.plusDays(i).getDayOfWeek().getDisplayName(TextStyle.FULL, getLocale());
            String dateString = String.valueOf(Model.now.plusDays(i).getDayOfMonth());
            DayContainer dc = new DayContainer(day, dateString, dayList, View.MONTH);
            this.add(dc);
        }
    }
    public void refresh(View v){
        this.removeAll();
        switch (v) {
            case View.WEEK:
                buildWeek();
                break;
            case View.MONTH:
                buildMonth();
                break;
            case View.YEAR:
                //TODO buildYear
            default:
                buildWeek();
                break;
        }
        this.repaint();
        this.revalidate();
    }
}
