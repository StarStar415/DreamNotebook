package course.java.project.dreamnotebook.controller.component.editFunction;

import course.java.project.dreamnotebook.controller.page.MainController;
import course.java.project.dreamnotebook.object.Notebook;
import course.java.project.dreamnotebook.object.Toast;
import course.java.project.dreamnotebook.object.ToastAnimationTime;
import course.java.project.dreamnotebook.object.ToastType;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SaveController implements EditFunction{
    private Notebook notebook;
    private TextArea textArea;

    public SaveController(Notebook notebook, TextArea textArea){
        this.notebook = notebook;
        this.textArea = textArea;
    }

    private String setNewFileName() throws Exception {
        // 跳出存檔視窗，輸入檔案名稱
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("儲存檔案");
        dialog.setHeaderText("請輸入檔名");
        dialog.setContentText("檔案名稱：");

        String fileName = dialog.showAndWait().orElseThrow(() -> new Exception());

        File file = new File("src/main/resources/NotebookFiles/" + fileName + ".json");

        // 確認檔名是否已存在
        if (file.exists()) {
            // 新增：是否覆寫
            Alert overwriteAlert = new Alert(Alert.AlertType.CONFIRMATION);
            overwriteAlert.setTitle("檔名已存在");
            overwriteAlert.setHeaderText("檔案名稱：" + fileName + " 已經存在。你要覆寫它嗎？");
            ButtonType overwriteResult = overwriteAlert.showAndWait().orElseThrow(() -> new Exception());
            if (overwriteResult != ButtonType.OK) {
                throw new Exception();
            }
        }

        return fileName;
    }

    public void run(){
        String filePath = "";
        Stage stage = (Stage) textArea.getScene().getWindow();

        // 確認是不是新建的檔案
        try {
            if (!notebook.getHasSaved()) {
                notebook.setTitle(setNewFileName());
            }

            filePath = "src/main/resources/NotebookFiles/" + notebook.getTitle() + ".json";
        }
        catch (Exception e){
            Toast.makeText(stage, "取消儲存", new ToastAnimationTime(), ToastType.INFO);
            return;
        }

        // 如果是新建的檔案，要先創一個空的 json 檔
        if (!notebook.getHasSaved()){
            try {
                // 創建空的 JSON 對象
                JSONObject emptyJsonObj = new JSONObject();

                // 寫入 JSON 文件
                FileWriter fileWriter = new FileWriter(filePath);
                fileWriter.write(emptyJsonObj.toString());
                fileWriter.flush();
                fileWriter.close();
            } catch (Exception e) {
                System.out.println("創建新建的空 JSON 文件時出現錯誤：" + e.getMessage());
                return;
            }
        }

        // 把內容寫入 json 檔
        try {
            Path path = Paths.get(filePath);
            System.out.println(filePath);
            String jsonContent = new String(Files.readAllBytes(path));
            JSONObject jsonObject = new JSONObject(jsonContent);

            // 修改JSON 中的某个 key 的值
            jsonObject.put("content", textArea.getText());
            jsonObject.put("lastModify", getNowTime());
            Files.write(path, jsonObject.toString().getBytes());

            Toast.makeText(stage, "儲存成功", new ToastAnimationTime(), ToastType.SUCCESS);
            notebook.setHasSaved(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getNowTime(){
        // 獲取現在的時間
        LocalDateTime now = LocalDateTime.now();
        // 定義日期時間格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 格式化時間為字串
        String formattedDateTime = now.format(formatter);
        return formattedDateTime;
    }

}
