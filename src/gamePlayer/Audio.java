package gamePlayer;

import java.io.IOException;
import javafx.scene.control.ScrollPane;
import javafx.scene.media.AudioClip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


/**
 * Audio Engine responsible for loading and playing audio files within the Game
 * Player
 * 
 * @author
 *
 */
public class Audio {
    
    private static final String DEFAULT_MUSIC = "/resources/music/Cut_Gee_VooGirls.mp3";
    private static final String SELECTION_MUSIC = "/resources/music/select.mp3";
    
    private AudioClip myDefault;
    

    public void playDefault() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        myDefault = playAudio(DEFAULT_MUSIC);
    }
    
    public void playSelection(){
        playAudio(SELECTION_MUSIC);
    }
    
    public void muteDefault(){
        System.out.println("mute default");
        myDefault.stop();
    }
    
//    public void resumeDefault(){
//        myDefault.setVolume(1.0);
//    }
    
    public AudioClip playAudio(String s){
        AudioClip clip = new AudioClip(this.getClass().getResource(s).toString());
        clip.play();
        return clip;
    }
    
    
//    public void play (int count) {
//        myClip.loop(count);
//    }
//
//    public void play () {
//        myClip.loop(Clip.LOOP_CONTINUOUSLY);
//    }
//
//    public void stop () {
//        myClip.stop();
//    }
}
