package course.java.project.dreamnotebook.controller.page;

import course.java.project.dreamnotebook.DreamNotebookApplication;
import course.java.project.dreamnotebook.controller.component.FxmlSwitchController;
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


    public MainController(){
    }

    public void initialize() {
        System.out.println(centerPane);
        FxmlSwitchController.setSubScreenRoot(centerPane);
        FxmlSwitchController.switchToFxml("/course/java/project/dreamnotebook/page/notebook-list-view.fxml");
    }
}