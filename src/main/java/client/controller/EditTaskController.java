package client.controller;

import client.Main;
import client.dto.TaskPriorityDtoResponse;
import client.dto.TaskStatusDtoResponse;
import client.model.SaveRequest;
import client.model.Task;
import client.model.TaskPriority;
import client.model.TaskStatus;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static client.Main.myHandler;

public class EditTaskController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea titleField;

    @FXML
    private Button cancelButton;

    @FXML
    private ChoiceBox<String> statusChoiceBox;

    @FXML
    private ChoiceBox<String> priorityChoiceBox;

    @FXML
    private Button saveButton;

    @FXML
    private TextArea textField;

    @FXML
    void initialize() {

        cancelButton.setOnAction(event -> Main.openNewScene("/startPage.fxml"));

        saveButton.setOnAction(event -> {
            boolean editMode = Main.currentTaskId != null;
            String request = "http://localhost:8080/api/tasks";
            if (editMode) request += "/" + Main.currentTaskId;
            request += "?title=" + titleField.getText()
                    + "&text=" + textField.getText()
                    + "&time=" + new Timestamp(System.currentTimeMillis())
                    + "&userId=1"
                    + "&status=" + statusChoiceBox.getValue()
                    + "&priority=" + priorityChoiceBox.getValue();
            if (editMode) request += "&id=" + Main.currentTaskId;
            request = request.replaceAll(" ", "%20");
            Main.EXECUTOR_SERVICE.execute(new SaveRequest(editMode, request));
            try {
                Thread.sleep(Main.DELAY_ON_BD_SAVE);
                Main.openNewScene("/startPage.fxml");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        try {
            String result = Request.Get("http://localhost:8080/statuses/").execute().handleResponse(myHandler);
            TaskStatusDtoResponse taskStatusDtoResponse = Main.GSON.fromJson(result, TaskStatusDtoResponse.class);
            List<TaskStatus> taskStatuses = taskStatusDtoResponse.getStatuses();
            List<String> statuses = taskStatuses.stream().map(TaskStatus::getStatus).collect(Collectors.toList());
            statusChoiceBox.getItems().addAll(statuses);

            result = Request.Get("http://localhost:8080/priorities/").execute().handleResponse(myHandler);
            TaskPriorityDtoResponse taskPriorityDtoResponse = Main.GSON.fromJson(result, TaskPriorityDtoResponse.class);
            List<TaskPriority> taskPriorities = taskPriorityDtoResponse.getPriorities();
            List<String> priorities = taskPriorities.stream().map(TaskPriority::getPriority).collect(Collectors.toList());
            priorityChoiceBox.getItems().addAll(priorities);

            if (Main.currentTaskId != null) {
                result = Request.Get("http://localhost:8080/api/tasks/" + Main.currentTaskId).execute().handleResponse(myHandler);
                Task task = Main.GSON.fromJson(result, Task.class);
                titleField.setText(task.getTitle());
                textField.setText(task.getText());
                statusChoiceBox.setValue(task.getStatus());
                priorityChoiceBox.setValue(task.getPriority());
            } else {
                statusChoiceBox.setValue(statuses.get(0));
                priorityChoiceBox.setValue(priorities.get(0));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}