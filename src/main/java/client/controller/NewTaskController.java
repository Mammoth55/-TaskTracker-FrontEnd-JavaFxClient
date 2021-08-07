package client.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class NewTaskController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField titleField;

    @FXML
    private Button cancelButton1;

    @FXML
    private Button saveButton;

    @FXML
    private TextField textField;

    @FXML
    void fafafa(ActionEvent event) {

    }

    @FXML
    void initialize() {
        saveButton.setOnAction(actionEvent -> {
            System.out.println("Нажата кнопка СОХРАНИТЬ");
        });
    }
}