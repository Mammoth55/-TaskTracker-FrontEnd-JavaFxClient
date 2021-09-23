package client;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.http.Consts;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main extends Application {

    public static final int MAIN_WINDOW_WIDTH = 990;
    public static final int MAIN_WINDOW_HEIGHT = 700;
    public static final int DELAY_ON_BD_SAVE = 555; // пауза чтобы доп.поток успел отработать в большинстве случаев и не понадобилось обновление окна при возвращении
    public static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(3);
    public static final Gson GSON = new Gson();
    public static Stage mainStage;
    public static Integer currentTaskId;
    public static ResponseHandler<String> myHandler = response -> EntityUtils.toString(response.getEntity(), Consts.UTF_8);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/startPage.fxml")));
        primaryStage.setTitle("CAPITAL TaskTracker Client v0.3");
        primaryStage.setScene(new Scene(root, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT));
        primaryStage.show();
        mainStage = primaryStage;

        primaryStage.setOnCloseRequest(t -> {
            EXECUTOR_SERVICE.shutdown();
            Platform.exit();
            System.exit(0);
        });
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
