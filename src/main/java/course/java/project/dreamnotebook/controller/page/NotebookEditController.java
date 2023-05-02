package course.java.project.dreamnotebook.controller.page;

import course.java.project.dreamnotebook.controller.component.editFunction.*;
import course.java.project.dreamnotebook.object.Notebook;
import course.java.project.dreamnotebook.object.Toast;
import course.java.project.dreamnotebook.object.ToastAnimationTime;
import course.java.project.dreamnotebook.object.ToastType;
import course.java.project.dreamnotebook.utils.KatexProcessor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.json.JSONObject;
import org.markdown4j.Markdown4jProcessor;

import java.io.File;
import java.io.FileWriter;
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

    @FXML
    private HBox italicsButton;
    @FXML
    private HBox saveButton;
    @FXML
    private HBox printButton;
    @FXML
    private HBox deleteButton;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        webView.getEngine().setUserStyleSheetLocation(getClass().getResource("/css/component/edit-webview.css").toString());

        textArea.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                String content = KatexProcessor.process(new Markdown4jProcessor().process(newValue));

                webView.getEngine().loadContent(content);
            }
            catch (StringIndexOutOfBoundsException | IOException e) {
                webView.getEngine().loadContent(newValue);
            }
        });

        // 斜體功能
        italicsButton.setOnMouseClicked(e -> {
            execEditFunction(new ItalicsController(textArea));
        });

        // 存檔功能 (ctrl+s 和 按鈕)
        textArea.setOnKeyPressed(e -> {
            if (pressCtrlS(e)) {
                execEditFunction(new SaveController(notebook, textArea));
            }
        });
        saveButton.setOnMouseClicked(e -> {
            execEditFunction(new SaveController(notebook, textArea));
        });
        

        // 影印功能
        printButton.setOnMouseClicked(e -> {
            execEditFunction(new PrintController(webView, textArea));
        });

        // 刪除功能
        deleteButton.setOnMouseClicked(e -> {
            execEditFunction(new DeleteController(notebook, textArea));
        });
    }

    private static boolean pressCtrlS(KeyEvent e) {
        return e.isControlDown() && e.getCode() == KeyCode.S;
    }

    public void setNotebook(Notebook notebook){
        this.notebook = notebook;
        textArea.setText(this.notebook.getContent());
    }

    private void setContent(String content){
        textArea.setText(content);
    }

    private void execEditFunction(EditFunction editFunction){
        editFunction.run();
    }
}