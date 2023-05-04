package course.java.project.dreamnotebook.utils;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class MusicPlayer {
    static private MediaPlayer mediaPlayer;
    static private Boolean autoReplay = true;

    private MusicPlayer(){
    }

    static public void init(){
//        setMusic("lofi-study-112191.mp3");
//        setVolume(1.0);
    }

    static public void setMusic(String musicFileName, double volume){
        Media sound = new Media(new File("src/main/resources/music/"+ musicFileName).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);

        if(autoReplay){
            mediaPlayer.setOnEndOfMedia(new Runnable() {
                public void run() {
                    mediaPlayer.seek(Duration.ZERO);
                    mediaPlayer.play();
                }
            });
        }

        setVolume(volume);
    }

    static public void setVolume(double volume){
        mediaPlayer.setVolume(volume);
    }

    static public void play(){
        mediaPlayer.play();
    }

    static public void stop(){
        mediaPlayer.stop();
    }
}
