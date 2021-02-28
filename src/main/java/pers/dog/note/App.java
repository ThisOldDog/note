package pers.dog.note;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pers.dog.note.component.application.ApplicationStatusManager;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    private static final String ROOT_FXML_NAME = "note-workspace";

    private ApplicationStatusManager applicationStatusManager;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        applicationStatusManager = new ApplicationStatusManager(stage);
        stage.setScene(new Scene(loadRoot()));
        applicationStatusManager.resumeStatus();
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        applicationStatusManager.storeStatus();
        super.stop();
    }

    private static Parent loadRoot() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(ROOT_FXML_NAME + ".fxml"));
        return fxmlLoader.load();
    }
}