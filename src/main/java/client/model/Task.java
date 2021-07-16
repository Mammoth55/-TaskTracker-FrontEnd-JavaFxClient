package client.model;

import java.sql.Timestamp;

public class Task {

    private int id;
    private String title;
    private String text;
    private Timestamp time;
    private int userId;
    private TaskStatus status;
    private TaskPriority priority;

    public Task() {
    }

    public Task(int id, String title, String text, Timestamp time, int userId, TaskStatus status, TaskPriority priority) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.time = time;
        this.userId = userId;
        this.status = status;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }
}