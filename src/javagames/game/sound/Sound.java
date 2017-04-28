package javagames.game.sound;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javagames.engine.util.ResourceLoader;

public class Sound {
	private byte[] music;
	private LoopEvent loopClip;
	private OneShotEvent oneShotClip;
	
	public Sound(String fileName) {
		InputStream in = ResourceLoader.load(getClass(),
				"./sound/" + fileName, "asdf");
		music = readBytes(in);
		loadWaveFile(music);
	}
	
	private byte[] readBytes(InputStream in) {
		try {
			BufferedInputStream buf = new BufferedInputStream(in);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int read;
			while ((read = buf.read()) != -1) {
				out.write(read);
			}
			in.close();
			return out.toByteArray();
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	private void loadWaveFile(byte[] rawData) {
		shutDownClips();
		loopClip = new LoopEvent(new BlockingClip(rawData));
		loopClip.initialize();
		oneShotClip = new OneShotEvent(new BlockingClip(rawData));
		oneShotClip.initialize();
	}
	
	private void shutDownClips() {
		if (loopClip != null)
			loopClip.shutDown();
		if (oneShotClip != null)
			oneShotClip.shutDown();
	}
	
	/**
	 * Plays the sound file specified in the constructor. Set loop to true to have the file played on loop.
	 * @param loop
	 */
	public void playSound(boolean loop) {
		if(loop) {
			loopClip.fire();
		}else{
			oneShotClip.fire();
		}
	}
	
	/**
	 * Stop the sound from playing
	 */
	public void stopSound() {
		loopClip.done();
		oneShotClip.done();
	}
}
