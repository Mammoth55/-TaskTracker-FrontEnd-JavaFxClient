package client.controller;

import client.Main;
import client.model.TableviewTask;
import client.model.Task;
import client.model.TaskDtoResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import org.apache.http.client.fluent.Request;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import static client.Main.myHandler;

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
                TableviewTask currentTask = tasksTable.getSelectionModel().getSelectedItem();
                textField.setText(currentTask.getText());
                Main.currentTaskId = currentTask.getId();
            }
        });

        createButton.setOnAction(event -> {
            Main.currentTaskId = null;
            openNewScene("/editTask.fxml");
        });

        editButton.setOnAction(event -> {
            if (Main.currentTaskId != null) {
                openNewScene("/editTask.fxml");
            }
        });

        deleteButton.setOnAction(event -> {
            if (Main.currentTaskId != null) {
                try {
                    Request.Delete("http://localhost:8080/api/tasks/" + Main.currentTaskId).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Main.openNewScene("/startPage.fxml");
            }
        });

        String result = null;
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
        Main.currentTaskId = null;
    }

    public void openNewScene(String fxmlPath) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlPath));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.mainStage.setScene(new Scene(loader.getRoot()));
        Main.mainStage.show();
    }
}