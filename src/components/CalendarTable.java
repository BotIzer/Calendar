package src.components;

import java.awt.Color;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import models.Model;
import models.Task;
import views.WindowBase.View;



public class CalendarTable extends JPanel{
    
    protected View view = View.WEEK;
    
    public CalendarTable(View v){
        if(v == View.WEEK){
            buildWeek();
        }
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
    }
    private void buildWeek(){
        int date = Model.now.getDayOfYear(); 
        for (int i = date - 3; i < date + 4; i++) {
            ArrayList<Task> dayList = new ArrayList<>();
            for (Task task : Model.tasks) {
                if (task.getStart().getDayOfYear() == i) {
                    dayList.add(task);
                }
            }
            String day = Model.now.plusDays(i-date).getDayOfWeek().getDisplayName(TextStyle.FULL, getLocale());
            String dateString = Model.now.plusDays(i-date).format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
            DayContainer dc = new DayContainer(day, dateString, dayList);
            this.add(dc);
        }
    };
    //TODO implement buildMonth and buildYear
    //private CalendarTable buildMonth(){};
    public void refresh(){
        this.removeAll();
        int date = Model.now.getDayOfYear(); 
        for (int i = date - 3; i < date + 4; i++) {
            ArrayList<Task> dayList = new ArrayList<>();
            for (Task task : Model.tasks) {
                if (task.getStart().getDayOfYear() == i) {
                    dayList.add(task);
                }
            }
            String day = Model.now.plusDays(i-date).getDayOfWeek().getDisplayName(TextStyle.FULL, getLocale());
            String dateString = Model.now.plusDays(i-date).format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
            DayContainer dc = new DayContainer(day, dateString, dayList);
            this.add(dc);
        }
        this.repaint();
        this.revalidate();
    }
}
