package course.java.project.dreamnotebook;

import course.java.project.dreamnotebook.utils.MusicPlayer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DreamNotebookApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DreamNotebookApplication.class.getResource("/course/java/project/dreamnotebook/page/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        stage.setTitle("DreamNotebook");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

        // set BGM
        MusicPlayer.setMusic("lofi-study-112191.mp3", 1.0);
        MusicPlayer.play();
    }

    public static void main(String[] args) {
        launch();
    }
}