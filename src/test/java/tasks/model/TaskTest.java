package tasks.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    private Task task;
    private Date start, end;
    private SimpleDateFormat sdf;

    @BeforeEach
    void setUp() {
        sdf= Task.getDateFormat();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void createTask(){
        try {
            start=sdf.parse("2020-04-05 22:00");
            Task task1 = new Task("lab", start);
            assert task1 != null;
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void createTask2(){
        try {
            start=sdf.parse("1969-04-05 22:00");
            Task task1 = new Task("lab", start);
            assert task1 == null;
        } catch (ParseException e) {
            fail(e.getMessage());
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }

    @Test
    public void createTask3(){
        try {
            start=sdf.parse("2020-04-05 22:00");
            end=sdf.parse("2020-04-15 22:00");
            Task task1 = new Task("lab", start, end, 1);
            assert task1 != null;
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void createTask4(){
        try {
            start=sdf.parse("2020-04-05 22:00");
            end=sdf.parse("2020-04-15 22:00");
            Task task1 = new Task("lab", start, end, -1);
            assert task1 == null;
        } catch (ParseException e) {
            fail(e.getMessage());
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }

    @Test
    public void F02_TC01(){
        try {
            start=sdf.parse("2020-04-05 22:00");
            end=sdf.parse("2020-04-05 22:00");
            Task task1 = new Task("lab", start, end, 3600);
            task1.setActive(true);
            assert task1.nextTimeAfter(sdf.parse("2020-04-05 22:00")) == null;
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void F02_TC02(){
        try {
            start=sdf.parse("2020-04-05 22:00");
            end=sdf.parse("2020-04-05 22:00");
            Task task1 = new Task("lab", start, end, 3600);
            task1.setActive(true);
            assert task1.nextTimeAfter(sdf.parse("2020-04-05 23:00")) == null;
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void F02_TC03(){
        try {
            start=sdf.parse("2020-04-05 21:00");
            end=sdf.parse("2020-04-05 22:00");
            Task task1 = new Task("lab", start, end, 3600);
            task1.setActive(true);
            assert task1.nextTimeAfter(sdf.parse("2020-04-05 20:00")).equals(sdf.parse("2020-04-05 21:00"));
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void F02_TC98(){
        try {
            start=sdf.parse("2020-04-05 21:00");
            end=sdf.parse("2020-04-05 22:00");
            Task task1 = new Task("lab", start);
            task1.setActive(true);
            assert task1.nextTimeAfter(sdf.parse("2020-04-05 20:00")).equals(sdf.parse("2020-04-05 21:00"));
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void F02_TC99(){
        try {
            start=sdf.parse("2020-04-05 21:00");
            end=sdf.parse("2020-04-05 22:00");
            Task task1 = new Task("lab", start);
            task1.setActive(false);
            assert task1.nextTimeAfter(sdf.parse("2020-04-05 20:00")) == null;
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }


//    @Test
//    void getTime() {
//        try {
//            Date time = sdf.parse("2020-04-05 22:00");
//            assert task.getTime() == time;
//        } catch (ParseException e) {
//            fail(e.getMessage());
//        }
//    }
}