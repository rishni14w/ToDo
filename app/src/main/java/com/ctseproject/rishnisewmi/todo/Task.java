package com.ctseproject.rishnisewmi.todo;

/**
 * Created by Rishni on 3/31/2018.
 */

public class Task {
    private String Task;
    private String Description;
    private String Date;

    public Task(String task,String description,String date)
    {
        Task=task;
        Description=description;
        Date=date;
    }

    public Task(String task,String date)
    {
        Task=task;
        Date=date;
    }

    public String getTask() {
        return Task;
    }

    public String getDescription() {
        return Description;
    }

    public String getDate() {
        return Date;
    }
}
