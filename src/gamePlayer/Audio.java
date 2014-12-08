package gamePlayer;

import java.io.IOException;
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
    
    AudioClip backgroundClip;
    // public Audio(String audioFileAddress) throws UnsupportedAudioFileException,
    // IOException, LineUnavailableException {
    // File audioFile = new File(audioFileAddress);
    // AudioInputStream audioStream = AudioSystem
    // .getAudioInputStream(audioFile);
    // myClip = AudioSystem.getClip();
    // myClip.open(audioStream);
    // }

    public void playDefault() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
//        backgroundClip = new AudioClip(this.getClass().getResource(DEFAULT_MUSIC).toString());
//        backgroundClip.setVolume(0.0);
//        backgroundClip.play();    
//        playAudio(DEFAULT_MUSIC);
        
        
    }
    
    public void playSelection(){
        System.out.println("selection");
        playAudio(SELECTION_MUSIC);
    }
    
    
    public void playAudio(String s){
        AudioClip defaultclip = new AudioClip(this.getClass().getResource(s).toString());
        defaultclip.play();
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
