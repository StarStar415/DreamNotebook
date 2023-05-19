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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class UploadImageController implements EditFunction{
    private TextArea textArea;

    public UploadImageController(TextArea textArea){
        this.textArea = textArea;
    }
    public void run(){
        Stage stage = (Stage) textArea.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("選取上傳圖片");
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            File destinationFile = new File("src/main/resources/images/upload/" + selectedFile.getName());
            try {
                Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                textArea.appendText("\n![](file:///" + destinationFile.getAbsolutePath() + ")"); // 在最後一行後面添加一個換行和圖片絕對路徑
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
