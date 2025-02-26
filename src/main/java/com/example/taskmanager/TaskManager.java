package com.example.taskmanager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Date;

/**
 * The TaskManager class is responsible for managing a collection of Task objects.
 * It provides methods to add, retrieve, and mark tasks as complete.
 */
public class TaskManager {
    private List<Task> tasks;

    /**
     * Constructs a new TaskManager object, initializing the internal list of tasks.
     */
    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a new task to the task manager.
     *
     * @param description The description of the task.
     * @param dueDate     The due date of the task.
     * @return The unique ID assigned to the newly created task.
     */
    public String addTask(String description, Date dueDate) {
        String taskId = UUID.randomUUID().toString();
        Task task = new Task(taskId, description, LocalDate.from(dueDate), false, (int)Math.max(0, (dueDate.getTime() - new Date().getTime()) / (1000 * 60)));
        tasks.add(task);
        return taskId;
    }

    /**
     * Retrieves a list of all tasks managed by this TaskManager.
     *
     * @return A List containing all tasks. A copy of the internal list is returned to prevent external modifications.
     */
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Marks a task as complete.
     *
     * @param taskId The ID of the task to mark as complete.
     * @return True if the task was found and marked as complete, false otherwise.
     */
    public boolean markComplete(String taskId) {
        for (Task task : tasks) {
            if (task.id().equals(taskId)) {
                int index = tasks.indexOf(task);
                if (index >= 0 && index < tasks.size()) {
                    Task currentTask = tasks.get(index);
                    Task updatedTask = new Task(currentTask.id(), currentTask.description(), currentTask.dueDate(), true, currentTask.pendingTime());
                    tasks.set(index, updatedTask);
                    return true;
                }
            }
        }
        return false;
    }

    public Task findTaskById(String taskId) {
        for (Task task : tasks) {
            if (task.id().equals(taskId)) {
                return task;
            }
        }
        return null;
    }

    public int totalPendingTime() {
        int totalTime = 0;
        for (Task task : tasks) {
            totalTime += task.pendingTime();
        }
        return totalTime;
    }
}