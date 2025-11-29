package test.java;


import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import main.java.models.Model;
import main.java.models.Task;

public class ModelTest {
    //FileHandler
    @BeforeAll
    public void setUp(){
        main.java.models.FileHandler.readFromJson();
    }
    //Task
    @Test
    public void getterTest(){
        Task t = new Task(LocalDateTime.now(), new Integer[]{1, 5}, "Title", "Description", false);
        Model.tasks.add(t);

        Task t2 = Model.tasks.getLast();
        assertEquals(t.getId(), t2.getId());
        assertEquals(t.getTitle(), "Title");
        assertEquals(t.getDescription(), "Description");
        assertEquals(t.getDuration()[0], t2.getDuration()[0]);
        assertEquals(t.getDuration()[0], 1);
        assertEquals(t.getDuration()[1], t2.getDuration()[1]);
        assertEquals(t.getDuration()[1], 5);
        assertEquals(t.getPriority(), false);
    }
    @Test
    public void setterTest() {
        Task t = Model.tasks.getLast();
        t.setTitle("New title");
        t.setDesc("New Description");
        t.setDuration(new Integer[]{2,0});
        t.setStart(LocalDateTime.now().plusDays(1), LocalDateTime.now());
        t.setPrio(true);

        assertEquals(t.getTitle(), "New title");
        assertEquals(t.getDescription(), "New Description");
        assertEquals(t.getDuration()[0], 2);
        assertEquals(t.getDuration()[1], 0);
        assertEquals(t.getStart().getDayOfYear(), LocalDateTime.now().plusDays(1).getDayOfYear());
        assertEquals(t.getPriority(), true);
    }
    @Test(expected = IllegalArgumentException.class)
    public void constraintTestTitle(){
        Task t = Model.tasks.getFirst();
        t.setTitle(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void constraintTestDuration(){
        Task t = Model.tasks.getFirst();
        t.setDuration(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void constraintTestDurationHour(){
        Task t = Model.tasks.getFirst();
        t.setDuration(new Integer[]{25, 0});
    }

    @Test(expected = IllegalArgumentException.class)
    public void constraintTestMinute(){
        Task t = Model.tasks.getFirst();
        t.setDuration(new Integer[]{1, 70});
    }

    @Test(expected = IllegalArgumentException.class)
    public void constraintTestStart(){
        Task t = Model.tasks.getFirst();
        t.setStart(LocalDateTime.now().minusDays(1), LocalDateTime.now());
    }
    @Test(expected = IllegalArgumentException.class)
    public void stringToDateFail(){
        Task.stringToLocalDate(null);
    } 

    @Test
    public void stringToDateSuccess(){
        LocalDateTime date = Task.stringToLocalDate("2025.11.29 18:25");

        assertEquals(date.toString(), "2025-11-29T18:25");
    }
    @Test
    public void makeDate(){
        LocalDateTime date = Task.makeDate(new Date(125,10,29), new Integer[]{18, 25});

        assertEquals(date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME), "2025-11-29T18:25:00");
    }
    //Model
    @Test
    public void modelGetter(){
        assertEquals("yyyy.MM.dd", Model.getDateFormat());
        assertEquals("HH:mm:ss", Model.getTimeFormat());
    }

}
