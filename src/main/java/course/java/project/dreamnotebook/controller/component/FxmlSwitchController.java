package course.java.project.dreamnotebook.controller.component;

import course.java.project.dreamnotebook.controller.page.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

import java.io.IOException;

public class FxmlSwitchController {
    static private Pane subScreenRoot;
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
        subScreenRoot.getChildren().setAll(fxmlNode);
    }

    public static void switchToFxml(Node root){
        subScreenRoot.getChildren().setAll(root);
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

    static public void setSubScreenRoot(Pane pane){
        subScreenRoot = pane;
    }
}
