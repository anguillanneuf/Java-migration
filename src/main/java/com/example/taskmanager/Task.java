package com.example.taskmanager;

import java.time.LocalDate;
import java.util.Date;

public record Task(
    String id,
    String description,
    LocalDate dueDate,
    boolean completed,
    int pendingTime
) {
    public Task(String id, String description, LocalDate dueDate, boolean completed, int pendingTime) {
        this.id = id;
        this.description = description;
        this.dueDate = dueDate;
        this.completed = completed;
        this.pendingTime = pendingTime;
    }

    public Task(String id, String description, Date dueDate, boolean completed) {
        this(id, description, LocalDate.from(dueDate), completed, (int)Math.max(0, (dueDate.getTime() - new Date().getTime()) / (1000 * 60)));
    }
}
