package models;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
//Table display class
public class TaskTableModel extends AbstractTableModel {
        private ArrayList<Task> subTasks; 
        public TaskTableModel(List<Task> t){
            subTasks = new ArrayList<>(t);
        }
        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0: return "Title";
                case 1: return "Description";
                case 2: return "Duration";
                case 3: return "Priority";
           
                default: return "Null";
            }
        
        }
        public int getColumnCount() { return 4; }
        public int getRowCount() { return subTasks.size();}
        public Object getValueAt(int row, int col) { 
            switch (col) {
                case 0: return subTasks.get(row).getTitle();
                case 1: return subTasks.get(row).getDescription();
                case 2: return String.valueOf(subTasks.get(row).getStart().getHour()) + ":" + subTasks.get(row).getStart().getMinute() + " - " + subTasks.get(row).getStart().plusHours(subTasks.get(row).getDuration()[0]).getHour() + ":" + subTasks.get(row).getStart().plusMinutes(subTasks.get(row).getDuration()[1]).getMinute();
                case 3: return Boolean.valueOf(subTasks.get(row).getPriority());
                default: return null;
        }
    }
}
