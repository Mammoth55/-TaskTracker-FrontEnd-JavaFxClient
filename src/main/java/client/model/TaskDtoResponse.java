package client.model;

import java.util.ArrayList;
import java.util.List;

public class TaskDtoResponse {

    private List<Task> tasks;

    public TaskDtoResponse() {
        this.tasks = new ArrayList<>();
    }

    public TaskDtoResponse(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}