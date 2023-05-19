package course.java.project.dreamnotebook.controller.page;

import course.java.project.dreamnotebook.controller.component.editFunction.*;
import course.java.project.dreamnotebook.object.Notebook;
import course.java.project.dreamnotebook.utils.KatexProcessor;
import course.java.project.dreamnotebook.utils.MusicPlayer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.markdown4j.Markdown4jProcessor;

import java.io.IOException;
import java.net.URL;
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
    private HBox boldButton;
    @FXML
    private HBox underlineButton;
    @FXML
    private HBox deletelineButton;
    @FXML
    private HBox saveButton;
    @FXML
    private HBox printButton;
    @FXML
    private HBox deleteButton;
    @FXML
    private HBox highlightButton;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private HBox changeTextColorButton;
    @FXML
    private ColorPicker colorPicker2;
    @FXML
    private HBox startMusicButton;
    @FXML
    private HBox pauseMusicButton;
    @FXML
    private HBox uploadImageButton;
    @FXML
    private HBox searchButton;

//    @FXML
//    private HBox colorpickerButton;


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

        //粗體功能
        boldButton.setOnMouseClicked(e -> {
            execEditFunction(new BoldController(textArea));
        });

        //底線功能
        underlineButton.setOnMouseClicked(e -> {
            execEditFunction(new UnderlineController(textArea));
        });

        //刪除線功能
        deletelineButton.setOnMouseClicked(e -> {
            execEditFunction(new DeletelineController(textArea));
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


        //螢光筆
        colorPicker.setValue(Color.web("98ff88"));
        colorPicker.setOnAction(e ->{
            execEditFunction(new ColorPickerController(colorPicker));
        });
        highlightButton.setOnMouseClicked(e -> {
            execEditFunction(new HighlightController(textArea,colorPicker));
        });

        //文字顏色
        colorPicker2.setValue(Color.web("98ff88"));
        colorPicker2.setOnAction(e ->{
            execEditFunction(new ColorPickerController(colorPicker2));
        });
        changeTextColorButton.setOnMouseClicked(e -> {
            execEditFunction(new ChangeTextColorController(textArea,colorPicker2));
        });

        // 影印功能
        printButton.setOnMouseClicked(e -> {
            execEditFunction(new PrintController(webView, textArea));
        });

        // 刪除功能
        deleteButton.setOnMouseClicked(e -> {
            execEditFunction(new DeleteController(notebook, textArea));
        });

        //音樂播放顯示
        setMusicStatus();

        //撥放音樂
        startMusicButton.setOnMouseClicked(e -> {
            MusicPlayer.play();
            //音樂播放顯示
            setMusicStatus();
        });

        //暫停音樂
        pauseMusicButton.setOnMouseClicked(e -> {
            MusicPlayer.stop();
            //音樂播放顯示
            setMusicStatus();
        });

        //上傳圖片
        uploadImageButton.setOnMouseClicked(e -> {
            execEditFunction(new UploadImageController(textArea));
        });

        //搜尋功能
        searchButton.setOnMouseClicked(e -> {
            execEditFunction(new SearchController(textArea));
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

    private void setMusicStatus(){
        startMusicButton.setManaged(!MusicPlayer.getNowMusicPlay());
        startMusicButton.setVisible(!MusicPlayer.getNowMusicPlay());
        pauseMusicButton.setManaged(MusicPlayer.getNowMusicPlay());
        pauseMusicButton.setVisible(MusicPlayer.getNowMusicPlay());
    }
}