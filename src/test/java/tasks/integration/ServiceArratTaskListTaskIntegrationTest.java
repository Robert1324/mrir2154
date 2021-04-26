package tasks.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import tasks.services.TasksService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.fail;

public class ServiceArratTaskListTaskIntegrationTest {

    private TasksService service;
    private ArrayTaskList repo;
    private Task task1, task2, task3;

    private Date start, end;
    private SimpleDateFormat sdf;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        sdf= Task.getDateFormat();
        try{
            start = sdf.parse("2020-04-05 22:00");
            end = sdf.parse("2020-04-06 22:00");
            task1 = new Task("task1", start, end, 1);
            task1.setActive(true);
            start = sdf.parse("2020-04-05 23:00");
            end = sdf.parse("2020-04-06 22:00");
            task2 = new Task("task2", start, end, 1);
            task2.setActive(true);
            start = sdf.parse("2020-04-06 23:00");
            end = sdf.parse("2020-04-07 22:00");
            task3 = new Task("task3", start, end, 1);
            task3.setActive(true);
        } catch (ParseException e){
            fail(e.getMessage());
        }
        repo = new ArrayTaskList();
        service = new TasksService(repo);

        repo.add(task1);
        repo.add(task2);
        repo.add(task3);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testFiltering(){
        try{
            start = sdf.parse("2020-04-05 23:00");
            end = sdf.parse("2020-04-06 22:00");
        } catch (ParseException e){
            fail(e.getMessage());
        }
        assert service.filterTasks(start,end).toString().equals("[" + task1.toString() + ", " + task2.toString() + "]");
    }

    @Test
    void testFiltering2(){
        try{
            start = sdf.parse("2020-04-07 23:00");
            end = sdf.parse("2020-04-07 23:30");
        } catch (ParseException e){
            fail(e.getMessage());
        }
        assert service.filterTasks(start,end).toString().equals("[]");
    }

}
