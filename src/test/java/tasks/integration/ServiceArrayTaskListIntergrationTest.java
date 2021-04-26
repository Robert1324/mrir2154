package tasks.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import tasks.services.TasksService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.fail;

public class ServiceArrayTaskListIntergrationTest {

    private TasksService service;
    private ArrayTaskList repo;

    @Mock
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
        } catch (ParseException e){
            fail(e.getMessage());
        }
        repo = new ArrayTaskList();
        service = new TasksService(repo);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testFiltering(){
        repo.add(task1);
        repo.add(task2);
        repo.add(task3);

        Mockito.when(task1.toString()).thenReturn("task1");
        Mockito.when(task2.toString()).thenReturn("task2");
        Mockito.when(task3.toString()).thenReturn("task3");

        Mockito.when(task1.nextTimeAfter(start)).thenReturn(start);
        Mockito.when(task2.nextTimeAfter(start)).thenReturn(end);
        Mockito.when(task1.nextTimeAfter(start)).thenReturn(null);

        assert service.filterTasks(start,end).toString().equals("[task2]");
    }

    @Test
    void testFiltering2(){
        repo.add(task1);
        repo.add(task2);
        repo.add(task3);

        Mockito.when(task1.toString()).thenReturn("task1");
        Mockito.when(task2.toString()).thenReturn("task2");
        Mockito.when(task3.toString()).thenReturn("task3");

        Mockito.when(task1.nextTimeAfter(start)).thenReturn(null);
        Mockito.when(task2.nextTimeAfter(start)).thenReturn(null);
        Mockito.when(task1.nextTimeAfter(start)).thenReturn(null);

        assert service.filterTasks(start,end).toString().equals("[]");
    }

}
