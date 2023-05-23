package course.java.project.dreamnotebook.controller.page;

import course.java.project.dreamnotebook.controller.component.editFunction.*;
import course.java.project.dreamnotebook.object.Notebook;
import course.java.project.dreamnotebook.utils.KatexProcessor;
import course.java.project.dreamnotebook.utils.MusicPlayer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import org.markdown4j.Markdown4jProcessor;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingController implements Initializable {
    @FXML
    private HBox startMusicButton;
    @FXML
    private HBox pauseMusicButton;
    @FXML
    private ComboBox<String> musicComboBox;
    @FXML
    private HBox uploadMusicButton;
    @FXML
    private TextArea textArea;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        updateComboBox();
        musicComboBox.setValue("leva-eternity-149473.mp3");
        // 監聽選取項目的變化
        musicComboBox.setOnAction(event -> {
            String selectedMusic = musicComboBox.getValue();
            MusicPlayer.setNowChangeMusic(true);
            musicComboBox.setValue(selectedMusic);
            MusicPlayer.stop();
            MusicPlayer.setMusic(selectedMusic, 1.0);
            MusicPlayer.play();
            setMusicStatus();
        });

        //初始音樂狀態設定
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

        uploadMusicButton.setOnMouseClicked(e -> {
            execEditFunction(new UploadMusicController(uploadMusicButton));
            updateComboBox();
            MusicPlayer.play();
            setMusicStatus();
        });
    }

    private void execEditFunction(EditFunction editFunction){
        editFunction.run();
    }

    private void updateComboBox(){
        musicComboBox.getItems().clear();
        File musicFolder = new File("src/main/resources/music/");
        if (musicFolder.exists() && musicFolder.isDirectory()) {
            File[] files = musicFolder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        musicComboBox.getItems().add(file.getName());
                    }
                }
            }
        }
    }

    private void setMusicStatus(){
        startMusicButton.setManaged(!MusicPlayer.getNowMusicPlay());
        startMusicButton.setVisible(!MusicPlayer.getNowMusicPlay());
        pauseMusicButton.setManaged(MusicPlayer.getNowMusicPlay());
        pauseMusicButton.setVisible(MusicPlayer.getNowMusicPlay());
    }
}