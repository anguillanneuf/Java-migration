package com.example.taskmanager;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TaskManagerTest {

    private TaskManager taskManager;

    @Before
    public void setUp() {
        taskManager = new TaskManager();
    }

    @Test
    public void testAddTask() {
        Date dueDate = new Date();
        String taskId = taskManager.addTask("Buy groceries", dueDate);
        assertNotNull(taskId);
        Task task = taskManager.findTaskById(taskId);
        assertNotNull(task);
        assertEquals("Buy groceries", task.description());
        assertEquals(LocalDate.from(dueDate), task.dueDate());
        assertFalse(task.completed());
    }

    @Test
    public void testGetAllTasks() {
        Date dueDate1 = new Date();
        taskManager.addTask("Task 1", dueDate1);
        Date dueDate2 = new Date();
        taskManager.addTask("Task 2", dueDate2);
        Date dueDate3 = new Date();
        taskManager.addTask("Task 3", dueDate3);

        List<Task> allTasks = taskManager.getAllTasks();
        assertEquals(3, allTasks.size());
    }

    @Test
    public void testMarkComplete() {
        Date dueDate = new Date();
        String taskId = taskManager.addTask("Test Task", dueDate);
        Task task = taskManager.findTaskById(taskId);
        assertNotNull(task);

        assertTrue(taskManager.markComplete(taskId));
        Task completedTask = taskManager.findTaskById(taskId);
        assertTrue(completedTask.completed());
        assertEquals("Test Task", completedTask.description());
        assertEquals(LocalDate.from(dueDate), completedTask.dueDate());
    }

    @Test
    public void testTotalPendingTime() {
        Date dueDate1 = new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1)); //due in one day
        taskManager.addTask("Task 1", dueDate1);

        Date dueDate2 = new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(3)); //due in 3 days
        taskManager.addTask("Task 2", dueDate2);


        Date dueDate3 = new Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1)); //due in the past
        taskManager.addTask("Task 3", dueDate3);


        int totalTime = taskManager.totalPendingTime();

        // Expecting approximately 24+72 hours, due to the time it takes to run the tests
        assertTrue(totalTime >= 24 * 60 && totalTime / 60 <= 100 * 60); //Allowing for some variation
    }
    
    @Test
    public void testMarkCompleteCorrectlyUpdatesTask() {
        Date dueDate = new Date();
        String taskId = taskManager.addTask("Test Task", dueDate);
        Task task = taskManager.findTaskById(taskId);
        assertNotNull(task);
        assertEquals("Test Task", task.description());
        assertFalse(task.completed());

        assertTrue(taskManager.markComplete(taskId));

        Task completedTask = taskManager.findTaskById(taskId);
        assertTrue(completedTask.completed());
        assertEquals("Test Task", completedTask.description());
        assertEquals(LocalDate.from(dueDate), completedTask.dueDate());
    }
}