package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;

public class Main extends Application {

    public static Stage mainStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/startPage.fxml")));
        primaryStage.setTitle("CAPITAL TaskTracker Client v0.2");
        primaryStage.setScene(new Scene(root, 990, 700));
        primaryStage.show();
        mainStage = primaryStage;
    }
}
