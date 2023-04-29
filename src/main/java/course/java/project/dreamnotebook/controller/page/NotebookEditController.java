package course.java.project.dreamnotebook.controller.page;

import course.java.project.dreamnotebook.object.Notebook;
import course.java.project.dreamnotebook.object.Toast;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.IndexRange;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.json.JSONObject;
import org.markdown4j.Markdown4jProcessor;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class NotebookEditController implements Initializable {
    private Notebook notebook;

    @FXML
    private TextArea textArea;

    @FXML
    private WebView webView;

    private int selectionStart;
    private int selectionEnd;

    @FXML
    private HBox italicsButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        webView.getEngine().setUserStyleSheetLocation(getClass().getResource("/css/component/edit-webview.css").toString());

        textArea.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                webView.getEngine().loadContent(new Markdown4jProcessor().process(newValue));
            }
            catch (StringIndexOutOfBoundsException | IOException e) {
                webView.getEngine().loadContent(newValue);
            }
        });

        // 斜體功能
        italicsButton.setOnMouseClicked(e -> {
            System.out.println("italic-click");
            handleItalicButtonAction(e);
        });

        // 存檔功能 (ctrl+s)
        textArea.setOnKeyPressed(e -> {
            if (e.isControlDown() && e.getCode() == KeyCode.S) {
                System.out.println("save");
                saveNotebook(); // call your save method
            }
        });
    }

    private void saveNotebook(){
        String filePath = "src/main/resources/NotebookFiles/"+notebook.getTitle()+".json";
        try {
            Path path = Paths.get(filePath);
            String jsonContent = new String(Files.readAllBytes(path));
            JSONObject jsonObject = new JSONObject(jsonContent);

            // 修改JSON 中的某个 key 的值
            jsonObject.put("content", textArea.getText());
            Files.write(path, jsonObject.toString().getBytes());

            Stage stage = (Stage) textArea.getScene().getWindow();
            Toast.makeText(stage, "儲存成功", 1500, 200, 200);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setNotebook(Notebook notebook){
        this.notebook = notebook;
        textArea.setText(this.notebook.getContent());
    }

    private void setContent(String content){
        textArea.setText(content);
    }

    @FXML
    private void handleItalicButtonAction(MouseEvent event){
        String selectedText = textArea.getSelectedText();
        String italicText = "*" + selectedText + "*";
        textArea.replaceSelection(italicText);
    }
}