package test.java;


import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import main.java.models.Model;
import main.java.models.Task;

public class ModelTest {
    @BeforeAll
    public void setUp(){
        main.java.models.FileHandler.readFromJson();
    }

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
}
