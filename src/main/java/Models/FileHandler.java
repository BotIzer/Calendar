package models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class FileHandler {
    public static void writeToJson(ArrayList<Task> model){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("tasks.json"));
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
    public static void readFromJson(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("tasks.json"));
            ArrayList<Task> tasks = new ArrayList<>();
            String entry;
            
            while ((entry = br.readLine()) != null) {
                tasks.add(Task.deSerialize(entry));
            }

            br.close();
            Model.tasks = tasks;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
