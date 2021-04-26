package tasks.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTaskListTest {

    private Task task;
    private Date start,end;
    private SimpleDateFormat sdf;
    private ArrayTaskList repo;

    @BeforeEach
    void setUp() {
        sdf= Task.getDateFormat();
        try{
            start = sdf.parse("2020-04-05 22:00");
            end = sdf.parse("2020-04-06 22:00");
        } catch (ParseException e){
            fail(e.getMessage());
        }
        repo = new ArrayTaskList();
        task = new Task("title", start, end, 1);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testAdd(){
        repo.add(task);
        assert repo.getTask(0).toString().equals(task.toString());
    }

    @Test
    void testSize(){
        assert repo.size() == 0;
        repo.add(task);
        assert repo.size() == 1;
        repo.add(task);
        assert repo.size() == 2;
        repo.remove(task);
        assert repo.size() == 1;
    }
}