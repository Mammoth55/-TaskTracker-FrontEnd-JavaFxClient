package client.dto;

import client.model.TaskPriority;

import java.util.ArrayList;
import java.util.List;

public class TaskPriorityDtoResponse {

    private List<TaskPriority> priorities;

    public TaskPriorityDtoResponse() {
        this.priorities = new ArrayList<>();
    }

    public TaskPriorityDtoResponse(List<TaskPriority> priorities) {
        this.priorities = priorities;
    }

    public List<TaskPriority> getPriorities() {
        return priorities;
    }

    public void setPriorities(List<TaskPriority> priorities) {
        this.priorities = priorities;
    }
}