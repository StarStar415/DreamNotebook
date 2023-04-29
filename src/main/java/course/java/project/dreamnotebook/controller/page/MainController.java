package course.java.project.dreamnotebook.controller.page;

import course.java.project.dreamnotebook.DreamNotebookApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MainController {
    @FXML private BorderPane rootPane;
    @FXML private Pane centerPane;
    @FXML private AnchorPane anchorPane;
    static public Pane subScreenRoot;

    public MainController(){
    }

    public void initialize() {
        System.out.println(centerPane);
        subScreenRoot = centerPane;
        loadFXML("/course/java/project/dreamnotebook/page/notebook-list-view.fxml");
    }


    // Load a new FXML file into the center pane
    private void loadFXML(String fxmlPath) {
        try {
            System.out.println(anchorPane);
            FXMLLoader loader = new FXMLLoader(DreamNotebookApplication.class.getResource(fxmlPath));
            loader.load();
            Node fxmlNode = loader.getRoot();
            subScreenRoot.getChildren().setAll(fxmlNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}