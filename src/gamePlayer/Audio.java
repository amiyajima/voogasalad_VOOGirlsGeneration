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
    
    private static final String DEFAULT_BACKGROUND_MUSIC = "/resources/music/Cut_Gee_VooGirls.mp3";
    private static final String DEFAULT_SELECTION_MUSIC = "/resources/music/select.mp3";
    
    private AudioClip myBGM;
    
    /**
     * Plays background music
     * @throws UnsupportedAudioFileException
     * @throws IOException
     * @throws LineUnavailableException
     */
    public void playBackground() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        myBGM = playAudio(DEFAULT_BACKGROUND_MUSIC);
    }
    
    /**
     * Plays "click" sound when valid items in the game are selected
     */
    public void playSelection(){
       playAudio(DEFAULT_SELECTION_MUSIC);
    }
    
    /**
     * Mutes background music
     */
    public void muteDefault(){
        myBGM.stop();
    }
    
    /**
     * Plays specified audio
     * @param s Audio file location
     * @return AudioClip
     */
    public AudioClip playAudio(String s){
        AudioClip clip = new AudioClip(this.getClass().getResource(s).toString());
        clip.play();
        return clip;
    }
}
