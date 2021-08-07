package client.controller;

import client.Main;
import client.dto.TaskDtoResponse;
import client.model.TableviewTask;
import client.model.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import org.apache.http.Consts;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.fluent.Request;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StartPageController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<TableviewTask> tasksTable;

    public TableColumn<TableviewTask, Integer> idColumn;
    public TableColumn<TableviewTask, String> timeColumn;
    public TableColumn<TableviewTask, String> priorityColumn;
    public TableColumn<TableviewTask, String> statusColumn;
    public TableColumn<TableviewTask, String> titleColumn;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private Button createButton;

    @FXML
    private TextField textField;

    @FXML
    void fafafa(ActionEvent event) {
    }

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tasksTable.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() > 0) {
                textField.setText(tasksTable.getSelectionModel().getSelectedItem().getText());
            }
        });

        createButton.setOnAction(event -> Main.openNewScene("/editTask.fxml"));

        String result = null;
        ResponseHandler<String> myHandler = response -> EntityUtils.toString(response.getEntity(), Consts.UTF_8);
        try {
            result = Request.Get("http://localhost:8080/api/tasks").execute().handleResponse(myHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson g = new Gson();
        Type type = new TypeToken<TaskDtoResponse>(){}.getType();
        TaskDtoResponse taskDtoResponse = g.fromJson(result, type);
        List<Task> tasks = taskDtoResponse.getTasks();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        priorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        ObservableList<TableviewTask> tableviewTasks = FXCollections.observableArrayList();
        for (Task task : tasks) {
            tableviewTasks.add(new TableviewTask(task.getId(), task.getTime().toString(), task.getPriority(),
                    task.getStatus(), task.getTitle(), task.getText(), task.getUserId()));
        }
        tasksTable.setItems(tableviewTasks);
    }
}