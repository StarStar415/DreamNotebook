package course.java.project.dreamnotebook.controller.component.editFunction;

import course.java.project.dreamnotebook.utils.MusicPlayer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class UploadMusicController implements EditFunction{

    private HBox button;
    public UploadMusicController(HBox button){
        this.button = button;
    }
    public void run(){
        Stage stage = (Stage) button.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("選擇音樂檔案");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("音樂檔案", "*.mp3", "*.wav"));
        File selectedFile = fileChooser.showOpenDialog(stage.getScene().getWindow());

        if (selectedFile != null) {
            try {
                File jarFile = null;
                try {
                    jarFile = new File(MusicPlayer.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
                File parentDirectory = jarFile.getParentFile();
                File musicFolder = new File(parentDirectory, "music");
//                String musicFolder = "src/main/resources/music/"; // 音樂資料夾路徑
                Path destination = Path.of(musicFolder.toString(), selectedFile.getName());
                Files.copy(selectedFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("音樂已成功上傳至 " + destination);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("音樂上傳失敗");
            }
        }
    }
}
