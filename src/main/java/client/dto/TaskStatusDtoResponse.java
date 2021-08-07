package client.dto;

import client.model.TaskStatus;

import java.util.ArrayList;
import java.util.List;

public class TaskStatusDtoResponse {

    private List<TaskStatus> statuses;

    public TaskStatusDtoResponse() {
        this.statuses = new ArrayList<>();
    }

    public TaskStatusDtoResponse(List<TaskStatus> statuses) {
        this.statuses = statuses;
    }

    public List<TaskStatus> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<TaskStatus> statuses) {
        this.statuses = statuses;
    }
}