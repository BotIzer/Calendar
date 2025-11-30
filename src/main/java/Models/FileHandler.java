package main.java.models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private FileHandler(){}
    public static void writeToJson(List<Task> model){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/tasks.json"));
            bw.write("[");
            String entry = "";
            for (int i = 0; i < model.size(); i++) {
                entry = model.get(i).serialize();
                if (i == model.size() - 1) {
                    bw.write(entry + "]");
                }else bw.write(entry + ",\n");
            }
            bw.close();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static List<Task> readFromJson(){
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/tasks.json"));
            String entry;
            int maxId = 0; 
            while ((entry = br.readLine()) != null) {
                tasks.add(Task.deSerialize(entry));
                if (tasks.getLast().getId() > maxId) {
                    maxId = tasks.getLast().getId();
                }
            }
            Task.idxIncr = maxId + 1;
            Model.tasks = tasks;
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tasks;
    }
}
