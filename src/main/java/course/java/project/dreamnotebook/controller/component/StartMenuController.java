package course.java.project.dreamnotebook.controller.component;

import course.java.project.dreamnotebook.controller.page.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.FileNotFoundException;

public class StartMenuController {
    @FXML
    Button homeButton;

    public void initialize()  {
        homeButton.setOnAction(e -> MainController.switchToFxml("/course/java/project/dreamnotebook/page/notebook-list-view.fxml"));
    }
}
