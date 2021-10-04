package client.dto;

import java.sql.Timestamp;

public class TaskDtoRequest {

    private int id;
    private String title;
    private String text;
    private Timestamp time;
    private int userId;
    private String status;
    private String priority;

    public TaskDtoRequest() {
    }

    public TaskDtoRequest(int id, String title, String text, Timestamp time, int userId, String status, String priority) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}