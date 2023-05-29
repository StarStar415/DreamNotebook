package course.java.project.dreamnotebook.controller.component;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartMenuController {
    @FXML
    private Button homeButton;

    @FXML
    private Button settingButton;

    public void initialize()  {
        //回首頁
        homeButton.setOnAction(e ->
            FxmlSwitchController.switchToFxml("/course/java/project/dreamnotebook/page/notebook-list-view.fxml"
        ));
        //去設定頁面
        settingButton.setOnAction(e ->
            FxmlSwitchController.switchToFxml("/course/java/project/dreamnotebook/page/setting-view.fxml"
        ));
    }
}
