package course.java.project.dreamnotebook.controller.page;

import course.java.project.dreamnotebook.DreamNotebookApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.*;

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

    public static void switchToFxml(String fxmlPath){
        // 建立一個新的FXMLLoader
        FXMLLoader loader = new FXMLLoader(MainController.class.getResource(fxmlPath));

        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        Node fxmlNode = loader.getRoot();
        // 不確定這行程式能不能用在所有 fxml，可能存在某些情況要額外包一層東西在 fxml 上
        HBox.setHgrow(fxmlNode, Priority.ALWAYS);
        MainController.subScreenRoot.getChildren().setAll(fxmlNode);
    }

    static public Node loadFxmlNode(String fxmlPath){
        FXMLLoader loader = new FXMLLoader(MainController.class.getResource(fxmlPath));

        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        Node fxmlNode = loader.getRoot();

        return fxmlNode;
    }
}