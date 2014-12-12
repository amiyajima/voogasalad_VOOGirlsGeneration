package gamePlayer;

import java.io.IOException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
    
    
    private MediaPlayer myBGM;
    
    /**
     * Plays background music
     * @throws UnsupportedAudioFileException
     * @throws IOException
     * @throws LineUnavailableException
     */
  public void playBackground() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
      myBGM = playAudio(DEFAULT_BACKGROUND_MUSIC);
      myBGM.setCycleCount(MediaPlayer.INDEFINITE);
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
    public MediaPlayer playAudio(String s){
        Media sound = new Media(this.getClass().getResource(s).toString());
        MediaPlayer player = new MediaPlayer(sound);
        player.play();
        return player;
    }
}
