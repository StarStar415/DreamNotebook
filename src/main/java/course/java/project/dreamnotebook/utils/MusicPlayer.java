package course.java.project.dreamnotebook.utils;

import course.java.project.dreamnotebook.controller.page.NotebookListController;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;

public class MusicPlayer {
    static private MediaPlayer mediaPlayer;
    static private Boolean autoReplay = true;

    static private String musicFileName;

    static private Boolean nowMusicPlay = true;

    static private Duration currentMediaTime;

    static private Boolean nowChangeMusic = false;

    static private double volume;

    private MusicPlayer(){
    }


    static public void setMusic(String musicFileName, double volume){
        File jarFile = null;
        try {
            jarFile = new File(MusicPlayer.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        File parentDirectory = jarFile.getParentFile();
        File musicFilesDirectory = new File(parentDirectory, "music");

        currentMediaTime = null;
        System.out.println(mediaPlayer);
        if (MusicPlayer.mediaPlayer != null) {
            MusicPlayer.mediaPlayer.stop();
            MusicPlayer.mediaPlayer.dispose();
        }
        Media sound = new Media(new File(musicFilesDirectory, musicFileName).toURI().toString());
        MusicPlayer.mediaPlayer = new MediaPlayer(sound);
        MusicPlayer.musicFileName = musicFileName;
        MusicPlayer.volume = volume;

        setVolume(volume);
    }

    static public String getMusicFileName(){return musicFileName;}

    static public void setVolume(double volume){
        mediaPlayer.setVolume(volume);
    }

    static public void play(){
        nowMusicPlay = true;
        if(nowChangeMusic==true){
            System.out.println("change!");
            currentMediaTime = null;
            nowChangeMusic=false;
        }
        if(autoReplay){
            mediaPlayer.setOnEndOfMedia(new Runnable() {
                public void run() {
                    MusicPlayer.setMusic(MusicPlayer.musicFileName, MusicPlayer.volume);
                    MusicPlayer.play();
                }
            });
        }

        if(currentMediaTime!=null) {
            mediaPlayer.setStartTime(currentMediaTime);
        }
        mediaPlayer.play();
    }

    static public void stop(){
        nowMusicPlay = false;
        if(mediaPlayer!=null){
            if(mediaPlayer.getCurrentTime()==null){
                currentMediaTime = null;
            }
            else {
                currentMediaTime = mediaPlayer.getCurrentTime();
            }
            mediaPlayer.stop();
        }
    }

    static public Boolean getNowMusicPlay() { return nowMusicPlay; }

    static public void setNowChangeMusic(Boolean change){
        nowChangeMusic = change;
    }
    static public void setNowMusicPlay(Boolean play){
        nowMusicPlay = play;
    }

    static public void setMusicFileName(String musicFileName){
        MusicPlayer.musicFileName = musicFileName;
    }
}
