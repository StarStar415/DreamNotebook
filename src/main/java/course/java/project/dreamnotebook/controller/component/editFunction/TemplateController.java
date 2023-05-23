package course.java.project.dreamnotebook.controller.component.editFunction;

import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextArea;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class TemplateController implements EditFunction {
    private TextArea textArea;
    private Stage dialogStage;
    private Dialog<Void> dialog;
    public TemplateController(TextArea textArea){
        this.textArea = textArea;
    }




    public void run() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/course/java/project/dreamnotebook/page/template-list-view.fxml"));
            Parent root = loader.load();

            // 創建跳出式窗
            dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setTitle("Template Dialog");
            dialogStage.setScene(new Scene(root));

            // 處理關閉事件
            dialogStage.setOnCloseRequest(event -> {
                closeDialog();
                event.consume();
            });

            // 顯示
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeDialog() {
        if (dialogStage != null) {
            dialogStage.close();
        }
    }
}
