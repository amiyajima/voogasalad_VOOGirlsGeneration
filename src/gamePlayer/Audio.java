package gamePlayer;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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
	private Clip myClip;

	public Audio(String audioFileAddress) throws UnsupportedAudioFileException,
			IOException, LineUnavailableException {
		File audioFile = new File(audioFileAddress);
		AudioInputStream audioStream = AudioSystem
				.getAudioInputStream(audioFile);
		myClip = AudioSystem.getClip();
		myClip.open(audioStream);
	}

	public void play(int count) {
		myClip.loop(count);
	}

	public void play() {
		myClip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void stop() {
		myClip.stop();
	}
}
