package course.java.project.dreamnotebook.controller.component.editFunction;

import course.java.project.dreamnotebook.controller.page.MainController;
import course.java.project.dreamnotebook.object.Notebook;
import course.java.project.dreamnotebook.object.Toast;
import course.java.project.dreamnotebook.object.ToastAnimationTime;
import course.java.project.dreamnotebook.object.ToastType;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class DeleteController implements EditFunction{
    private Notebook notebook;
    private Node node;

    public DeleteController(Notebook notebook, Node node){
        this.notebook = notebook;
        this.node = node;
    }

    public void run(){
        String fileName = this.notebook.getTitle();
        Stage stage = (Stage) this.node.getScene().getWindow();

        if (!this.notebook.getHasSaved()){
            Toast.makeText(stage, "該文件尚未儲存", new ToastAnimationTime(), ToastType.INFO);
            return;
        }

        File file = new File("src/main/resources/NotebookFiles/" + fileName + ".json");

        Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
        deleteAlert.setTitle("刪除檔案");
        deleteAlert.setHeaderText("確定要刪除 " + fileName + " 嗎？");
        ButtonType deleteResult = deleteAlert.showAndWait().orElse(null);

        if (deleteResult == ButtonType.OK) {
            try {
                Files.delete(file.toPath());
                Toast.makeText(stage, "檔案已刪除", new ToastAnimationTime(), ToastType.INFO);
                MainController.switchToFxml("/course/java/project/dreamnotebook/page/notebook-list-view.fxml");
            } catch (IOException e) {
                Toast.makeText(stage, "發生錯誤", new ToastAnimationTime(), ToastType.ERROR);
            }
        }
    }
}
