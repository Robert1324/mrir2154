package tasks.services;

import javafx.collections.FXCollections;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tasks.model.ArrayTaskList;
import tasks.model.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TasksServiceTest {

    private Task task;
    private Date start, start1,end, end1;
    private SimpleDateFormat sdf;

    @Mock
    private ArrayTaskList repo;

    @InjectMocks
    private TasksService service;

    @BeforeEach
    void setUp() {
        sdf= Task.getDateFormat();
        MockitoAnnotations.initMocks(this);
        try{
            start = sdf.parse("2020-04-05 22:00");
            start1 = sdf.parse("2020-04-05 21:00");
            end = sdf.parse("2020-04-06 22:00");
            end1 = sdf.parse("2020-04-06 23:00");
        } catch (ParseException e){
            fail(e.getMessage());
        }
        task = new Task("title", start, end, 1);
        task.setActive(true);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testEmpty(){
        System.out.println(service.getObservableList());
        assert service.getObservableList().isEmpty();
    }

    @Test
    void testFilter(){
        Mockito.when(repo.getAll()).thenReturn(Arrays.asList(task));
        ArrayList<Task> temp = new ArrayList<Task>();
        temp.add(task);
        assert service.filterTasks(start1,end1).equals(temp);
    }
}