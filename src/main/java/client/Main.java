package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.http.Consts;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    private static final int MAIN_WINDOW_WIDTH = 990;
    private static final int MAIN_WINDOW_HEIGHT = 700;
    public static Stage mainStage;
    public static Integer currentTaskId;
    public static ResponseHandler<String> myHandler = response -> EntityUtils.toString(response.getEntity(), Consts.UTF_8);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/startPage.fxml")));
        primaryStage.setTitle("CAPITAL TaskTracker Client v0.2");
        primaryStage.setScene(new Scene(root, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT));
        primaryStage.show();
        mainStage = primaryStage;
    }

    public static void openNewScene(String fxmlPath) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource(fxmlPath));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainStage.setScene(new Scene(loader.getRoot()));
        mainStage.show();
    }
}
