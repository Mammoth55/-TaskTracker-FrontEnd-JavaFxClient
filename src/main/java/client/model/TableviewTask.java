package client.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableviewTask {

    private SimpleIntegerProperty id;
    private SimpleStringProperty time;
    private SimpleStringProperty priority;
    private SimpleStringProperty status;
    private SimpleStringProperty title;
    private SimpleStringProperty text;
    private SimpleIntegerProperty userId;

    public TableviewTask() {
    }

    public TableviewTask(Integer id, String time, String priority, String status, String title, String text, Integer userId) {
        this.id = new SimpleIntegerProperty(id);
        this.title = new SimpleStringProperty(title);
        this.time = new SimpleStringProperty(time);
        this.status = new SimpleStringProperty(status);
        this.priority = new SimpleStringProperty(priority);
        this.text = new SimpleStringProperty(text);
        this.userId = new SimpleIntegerProperty(userId);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getPriority() {
        return priority.get();
    }

    public SimpleStringProperty priorityProperty() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority.set(priority);
    }

    public String getText() {
        return text.get();
    }

    public SimpleStringProperty textProperty() {
        return text;
    }

    public void setText(String text) {
        this.text.set(text);
    }

    public int getUserId() {
        return userId.get();
    }

    public SimpleIntegerProperty userIdProperty() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId.set(userId);
    }
}